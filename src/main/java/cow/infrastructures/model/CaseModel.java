package cow.infrastructures.model;

import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import cow.config.CaseConfig;
import cow.infrastructures.converter.CaseConverter;
import cow.infrastructures.jooq.tables.CaseResult;
import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.repository.CaseResultRepository;
import cow.infrastructures.repository.UserDefineParamRepository;
import cow.infrastructures.struct.ido.CaseResultIDO;
import cow.infrastructures.struct.vo1.CaseDetailVO;
import cow.infrastructures.struct.vo1.CaseQueryVO;
import cow.infrastructures.struct.vo1.CaseResultVO;
import cow.infrastructures.struct.vo1.UserDefineParamVO;
import cow.infrastructures.util.BusinessException;
import cow.infrastructures.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
@Scope("request")
public class CaseModel {

    private final OkHttpClient client = new OkHttpClient();

    private final UserDefineParamRepository userDefineParamRepository;
    private Map<String, String> userDefineParamMap = new HashMap();

    private CaseConfig caseConfig;

    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private CaseResultVO caseResultVO = new CaseResultVO();
    private CaseDetailVO caseDetailVO;

    public CaseModel(CaseDetailRepository caseDetailRepository, CaseConverter caseConverter, CaseResultRepository caseResultRepository, UserDefineParamRepository userDefineParamRepository, JsonUtil jsonUtil) {

        this.userDefineParamRepository = userDefineParamRepository;
    }

    public CaseModel doHttpRequest(CaseDetailVO caseDetailVO) {
        this.caseDetailVO = caseDetailVO;
        Request.Builder builder = new Request.Builder();
        Request request = null;
        String header = replaceParameters(caseDetailVO.getHeader());
        String url = replaceParameters(caseDetailVO.getUrl());
        String data = replaceParameters(caseDetailVO.getData());
        if (StringUtils.isNullOrEmpty(header)) {
            request = builder.url(caseDetailVO.getUrl()).get().build();
        }


        if(StringUtils.isNullOrEmpty(header)){
          header="accept: */*";
        }
        JSONObject headers=new JSONObject(header);


        headers.keySet().forEach(keys -> {
            builder.header(keys, headers.getString(keys));
                }
        );
        if (!headers.has("Content-Type")) {
            request = builder.url(caseDetailVO.getUrl()).get().build();
        }
        RequestBody body=null;
        if(!StringUtils.isNullOrEmpty(data)){
            body = (RequestBody) RequestBody.create(JSON, data);
        }
        switch (caseDetailVO.getMethod()) {
            case "POST": request = builder.url(url).post(body).build();break;
            case "PUT": request = builder.url(url).put(body).build();break;
            case "DELETE": request = builder.url(url).delete(body).build();break;
            default:request = builder.url(url).build();break;
        }
        try {
            //发送请求
            Response response = client.newCall(request).execute();
            String responseResult = response.body().string();
            log.info(responseResult.toString());
            url = response.request().url().toString();
            log.info(response.request().url().toString());
            CaseResultVO caseResultVO = new CaseResultVO();

            caseResultVO.setUrl(url);
            caseResultVO.setResponseResult(responseResult);
            caseResultVO.setCaseGroupId(caseDetailVO.getGroupId());
            caseResultVO.setCaseId(caseDetailVO.getId());
            caseResultVO.setMethod(caseDetailVO.getMethod());
            caseResultVO.setPath(caseDetailVO.getPath());
            caseResultVO.setHeader(response.request().headers().toString());
            caseResultVO.setHttpStatusCode(response.code());
            if ( response.request().body() !=null) {
                caseResultVO.setData(response.request().body().toString());
            }
            this.caseResultVO = caseResultVO;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
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

    public CaseModel doExtraction() throws BusinessException {
        String result =caseResultVO.getResponseResult();
        if(StringUtils.isNullOrEmpty( result)
                ||StringUtils.isNullOrEmpty(caseDetailVO.getExtract())
                ||caseResultVO.getHttpStatusCode()!=200
        ) {

            //todo 处理返回结果为空
            return this;
        }
            ;
        List<UserDefineParamVO> userDefineParamVOS = new ArrayList<>();
        UserDefineParamVO userDefineParamVO = new UserDefineParamVO();
        String extract = caseDetailVO.getExtract();
        //获得存储变量名及变量的path
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
                log.error("提取路径不存在:"+caseDetailVO.getExtract()+"返回结果："+result);
                //todo 处理jsonpath不存在
            }
        });

        return this;
    }

    //断言处理
    public  CaseModel doAssert()  {
//        JSONObject expecteds= jsonUtil.toEntity(caseDetailVO.getAssertions(),JSONObject.class);
        String resultResponce = caseResultVO.getResponseResult();

       if(caseResultVO.getHttpStatusCode()!=200
        ||StringUtils.isNullOrEmpty( resultResponce)
               ||StringUtils.isNullOrEmpty(caseDetailVO.getAssertions())) {
           //todo 处理返回结果为空
           return this;
       }
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
        return this;
    }

    public CaseModel initUserDefineParamMap(CaseQueryVO caseQueryVO){
        //查询自定义变量
        List<UserDefineParamVO> userDefineParams = userDefineParamRepository.seach(caseQueryVO);
        userDefineParamMap = userDefineParams.stream().collect(
                Collectors.toMap(UserDefineParamVO::getParamName, UserDefineParamVO::getParamValue, (key1, key2) -> key2)
        );
        return this;
    }
    public CaseResultVO getCaseResultVO(){
        return this.caseResultVO;
    }

}
