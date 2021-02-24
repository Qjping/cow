package cow.applications.service;

import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import cow.infrastructures.converter.CaseConverter;


import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.repository.CaseResultRepository;

import cow.infrastructures.repository.UserDefineParamRepository;
import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseDetailIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.*;

import cow.infrastructures.util.BusinessException;
import cow.infrastructures.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CaseDetailService {
    private final CaseDetailRepository caseDetailRepository;
    private final CaseConverter caseConverter;
    private final OkHttpClient client = new OkHttpClient();
    private final CaseResultRepository caseResultRepository;
    private final UserDefineParamRepository userDefineParamRepository;
    private final JsonUtil jsonUtil;
    private Map<String, String> userDefineParamMap = new HashMap();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public CaseDetailService(CaseDetailRepository caseDetailRepository, CaseConverter caseConverter, CaseResultRepository caseResultRepository, UserDefineParamRepository userDefineParamRepository, JsonUtil jsonUtil) {
        this.caseDetailRepository = caseDetailRepository;
        this.caseConverter = caseConverter;
        this.caseResultRepository = caseResultRepository;
        this.userDefineParamRepository = userDefineParamRepository;
        this.jsonUtil = jsonUtil;
    }

    public PageResultIDO<CaseDetailIDO> searchCaseDetailList(CaseQueryIDO caseQueryIDO) {
        CaseQueryVO caseQueryVO = caseConverter.caseQueryIdoToVo(caseQueryIDO);
        PageResultVO<CaseDetailVO> pageResultVO = caseDetailRepository.getCaseDetailListByCondition(caseQueryVO);
        List<CaseDetailVO> caseDetailVOS = pageResultVO.getList();

        return new PageResultIDO(caseConverter.caseDetailVoListToIDO(caseDetailVOS), pageResultVO.getCount());

    }


    @Transactional
    public void addCase(CaseDetailAddIDO caseQueryIDO) {
        CaseDetailAddVO caseDetailAddVO = caseConverter.caseDetailIdoToVo(caseQueryIDO);
        caseDetailRepository.saveCase(caseDetailAddVO);
    }

    @Transactional
    public void addCase(List<CaseDetailAddIDO> caseDetailAddIDOList) {
        List<CaseDetailAddVO> caseDetailAddVOList = caseConverter.caseDetailIdoListToVoList(caseDetailAddIDOList);
        caseDetailRepository.saveCase(caseDetailAddVOList);
    }

     @Async
    public void execute(CaseQueryIDO caseQueryIDO) {
        CaseQueryVO caseQueryVO = caseConverter.caseQueryIdoToVo(caseQueryIDO);
        PageResultVO<CaseDetailVO> pageResultVO = caseDetailRepository.getCaseDetailListByCondition(caseQueryVO);
        List<CaseDetailVO> caseDetailVOS = pageResultVO.getList();
        List<CaseResultVO> caseResultList = new ArrayList<CaseResultVO>();
        //查询自定义变量

        List<UserDefineParamVO> userDefineParams = userDefineParamRepository.seach(caseQueryVO);
        userDefineParamMap = userDefineParams.stream().collect(
                Collectors.toMap(UserDefineParamVO::getParamName, UserDefineParamVO::getParamValue, (key1, key2) -> key2)
        );
        //后期通过用户规则提取的变量
        List<UserDefineParamVO> ruleUserDefineParamVOS = new ArrayList<>();
        log.info("caseDetailVOS:"+caseDetailVOS.toString());
        AtomicReference<Integer> passCount= new AtomicReference<>(0);
        //发送请求
        caseDetailVOS.forEach(caseDetailVO -> {
                    try {
                        //发送请求
                        Response response = client.newCall(doHttpRequest(caseDetailVO)).execute();
                        CaseResultVO caseResultVO = new CaseResultVO();
                        if(response.code()==200){
                            passCount.getAndSet(passCount.get() + 1);
                            if ( response.request().body() !=null) {
                                caseResultVO.setData(response.request().body().toString());
                            }
                            String responseResult = response.body().string();
                            //保存结果
                            caseResultVO.setUrl(response.request().url().toString());
                            caseResultVO.setResponseResult(responseResult);
                            caseResultVO.setCaseGroupId(caseDetailVO.getGroupId());
                            caseResultVO.setCaseId(caseDetailVO.getId());
                            caseResultVO.setMethod(caseDetailVO.getMethod());
                            caseResultVO.setPath(caseDetailVO.getPath());
                            caseResultVO.setHeader(response.request().headers().toString());
                            caseResultList.add(caseResultVO);
                            //提取参数
                            if(!StringUtils.isNullOrEmpty(caseDetailVO.getExtract())){
                                List<UserDefineParamVO> userDefineParamVOList = doExtraction(caseDetailVO, responseResult);
                                ruleUserDefineParamVOS.addAll(userDefineParamVOList);
                            }

                            if(!StringUtils.isNullOrEmpty(caseDetailVO.getAssertions())){
                                //todo 断言
                                doAssert(caseDetailVO,responseResult);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        caseResultRepository.save(caseResultList);
        userDefineParamRepository.save(ruleUserDefineParamVOS);

    }

    public Request doHttpRequest(CaseDetailVO caseDetailVO) {
        Request.Builder builder = new Request.Builder();
        Request request = null;
        String header = replaceParameters(caseDetailVO.getHeader());
        String url = replaceParameters(caseDetailVO.getUrl());
        String data = replaceParameters(caseDetailVO.getData());
        if (StringUtils.isNullOrEmpty(header)) {
            return builder.url(caseDetailVO.getUrl()).get().build();
        }
        JSONObject headers = new JSONObject(header);
        headers.keySet().forEach(keys -> {
            builder.header(keys, headers.getString(keys));
                }
        );

        if (!headers.has("Content-Type")) {
            return builder.url(caseDetailVO.getUrl()).get().build();
        }
        RequestBody body = RequestBody.create(data, JSON);
        switch (caseDetailVO.getMethod()) {
            case "POST":request = builder.url(url).post(body).build();break;
            case "PUT":request = builder.url(url).put(body).build();break;
            case "DELETE": request = builder.url(url).delete(body).build();break;
            default:request = builder.url(url).build();break;
        }
        return request;
    }

    public String replaceParameters(String string) {
        if (StringUtils.isNullOrEmpty(string)) {
            return string;
        }
        String str = "\\$\\{.*?}";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(string);
        while (m.find()) {
            //去全局变量map里查
            String newStr = m.group().replace("${", "").replace("}", "");
            log.info(userDefineParamMap.toString());
            if(userDefineParamMap.containsKey(newStr)){
                string = string.replace(m.group(), userDefineParamMap.get(newStr));
            }else {
                log.error("全局变量未有此变量："+string);
            }

        }
        return string.replaceAll("\r|\n", "");
    }

    private List<UserDefineParamVO> doExtraction(CaseDetailVO caseDetailVO, String result) throws BusinessException, JsonProcessingException {
        List<UserDefineParamVO> userDefineParamVOS = new ArrayList<>();
        UserDefineParamVO userDefineParamVO = new UserDefineParamVO();
        String extract = caseDetailVO.getExtract();
            //获得存储变量名及变量的path
        //

            log.error(extract.toString());
            JSONObject extrats = new JSONObject(extract);

            extrats.toMap().forEach((paramName,paramValue)->{
                // 从返回结果中获取变量值fasjson
                if((JSONPath.read(result, (String) paramValue)!=null)){
                    String variableValue = JSONPath.extract(result,(String) paramValue).toString();
                    userDefineParamVO.setCaseGroupId(caseDetailVO.getGroupId());
                    userDefineParamVO.setParamName(paramName);
                    userDefineParamVO.setParamValue(variableValue);
                    userDefineParamVOS.add(userDefineParamVO);
                    userDefineParamMap.put(paramName, variableValue);
                }else {
                    log.info("提取路径不存在:"+caseDetailVO.getExtract()+"返回结果："+result);
                    //todo 处理jsonpath不存在

                }
            });

        return userDefineParamVOS;
    }

    //断言处理

    /**
     *
     * @param caseDetailVO
     * @param resultResponce
     * @throws IOException
     */
     public  void doAssert(CaseDetailVO caseDetailVO, String resultResponce) throws IOException {

//        JSONObject expecteds= jsonUtil.toEntity(caseDetailVO.getAssertions(),JSONObject.class);
        JSONObject expecteds=new JSONObject((caseDetailVO.getAssertions()));
        log.info("断言规则:"+expecteds.toString());
        expecteds.keys().forEachRemaining(actualVaule->{
            expecteds.getString(actualVaule);
            if(JSONPath.extract(resultResponce,expecteds.getString(actualVaule))!=null) {
                String expectedValue = JSONPath.extract(resultResponce, expecteds.getString(actualVaule)).toString();
                String regex = "\\$\\{(.*?)\\}";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(expectedValue);
                if (m.find()) {
                    actualVaule = actualVaule.replace("${", "").replace("}", "");
                    if (!userDefineParamMap.get(actualVaule).equals(expectedValue)) {
                        log.info("assert匹配失败用例id:" + caseDetailVO.toString() + ",期望的值：" + expectedValue);
                    }
                    //               Assert.doAssert(CaseConfig.globalParam.get(expected), expecteds);
                } else {
                    if (!actualVaule.equals(expectedValue)) {
                        log.info("assert匹配失败用例id:" + caseDetailVO.toString() + ",期望的值：" + expectedValue);
                    }
                    //                Assert.doAssert(expecteds.getString(path), CaseConfig.globalParam.get(expected));
                }
            }else {
                log.error("断言值未找到:"+caseDetailVO.toString());
            }
        });

    }

}


