package cow.applications.service;

import com.alibaba.fastjson.JSONPath;
import com.mysql.cj.util.StringUtils;
import cow.infrastructures.converter.CaseDetailconverter;


import cow.infrastructures.jooq.tables.CaseConfig;
import cow.infrastructures.jooq.tables.UserDefineParam;
import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.repository.CaseResultRepository;

import cow.infrastructures.repository.UserDefineParamRepository;
import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.*;

import okhttp3.*;

import org.json.JSONObject;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CaseDetailService {
    private final CaseDetailRepository caseDetailRepository;
    private final CaseDetailconverter caseDetailconverter;
    private final OkHttpClient client = new OkHttpClient();
    private final CaseResultRepository caseResultRepository;
    private final UserDefineParamRepository userDefineParamRepository;
    private Map<String, String> userDefinParamMap = new HashMap();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public CaseDetailService(CaseDetailRepository caseDetailRepository, CaseDetailconverter caseDetailconverter, CaseResultRepository caseResultRepository, UserDefineParamRepository userDefineParamRepository) {
        this.caseDetailRepository = caseDetailRepository;
        this.caseDetailconverter = caseDetailconverter;
        this.caseResultRepository = caseResultRepository;
        this.userDefineParamRepository = userDefineParamRepository;
    }

    public PageResultIDO<CaseQueryIDO> searchCaseDetailList(CaseQueryIDO caseQueryIDO) {
        CaseQueryVO caseQueryVO = caseDetailconverter.caseQueryIdoTovo(caseQueryIDO);
        PageResultVO<CaseDetailVO> pageResultVO = caseDetailRepository.getCaseDetailListByCondition(caseQueryVO);
        List<CaseDetailVO> caseDetailVOS = pageResultVO.getList();

        return new PageResultIDO(caseDetailVOS, pageResultVO.getCount());

    }


    @Transactional
    public void addCase(CaseDetailAddIDO caseQueryIDO) {
        CaseDetailAddVO caseDetailAddVO = caseDetailconverter.caseDetailidoTovo(caseQueryIDO);
        caseDetailRepository.addCase(caseDetailAddVO);
    }

    //TODO 提取参数，
    public void excute(CaseQueryIDO caseQueryIDO) {
        CaseQueryVO caseQueryVO = caseDetailconverter.caseQueryIdoTovo(caseQueryIDO);
        PageResultVO<CaseDetailVO> pageResultVO = caseDetailRepository.getCaseDetailListByCondition(caseQueryVO);
        List<CaseDetailVO> caseDetailVOS = pageResultVO.getList();
        List<CaseResultVO> caseResultList = new ArrayList<CaseResultVO>();
        //查询自定义变量

        List<UserDefineParamVO> userDefineParams = userDefineParamRepository.seach(caseQueryVO);
        userDefinParamMap = userDefineParams.stream().collect(
                Collectors.toMap(UserDefineParamVO::getParamName, UserDefineParamVO::getParamValue, (key1, key2) -> key2)
        );

        //发送请求
        caseDetailVOS.forEach(caseDetailVO -> {
                    try {
                        String header = replaceParemeters(caseDetailVO.getHeader());
                        String url = replaceParemeters(caseDetailVO.getUrl());
                        String data = replaceParemeters(caseDetailVO.getData());
                        //发送请求
                        Response response = client.newCall(requestHttp(caseDetailVO)).execute();
                        CaseResultVO caseResultVO = new CaseResultVO();
                        //提取参数
                        if ( response.request().body() !=null) {
                            caseResultVO.setData(data);
                        }
                        String responseCase = response.body().string();
                        extraction(caseDetailVO,responseCase);

                        //保存结果
                        Integer code = response.code();
                        Headers headers = response.headers();


                        caseResultVO.setUrl(url);
                        caseResultVO.setResponseResult(responseCase);
                        caseResultVO.setCaseGroupId(caseDetailVO.getGroupId());
                        caseResultVO.setCaseId(caseDetailVO.getId());
                        caseResultVO.setMethod(caseDetailVO.getMethod());
                        caseResultVO.setPath(caseDetailVO.getPath());
                        caseResultVO.setHeader(header);

                        caseResultList.add(caseResultVO);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        caseResultRepository.save(caseResultList);

    }

    public Request requestHttp(CaseDetailVO caseDetailVO) {
        Request.Builder builder = new Request.Builder();
        Request request = null;
        String header = replaceParemeters(caseDetailVO.getHeader());
        String url = replaceParemeters(caseDetailVO.getUrl());
        String data = replaceParemeters(caseDetailVO.getData());
//        if (StringUtils.isNullOrEmpty(header)) {
//            return builder.url(caseDetailVO.getUrl()).get().build();
//        }
        JSONObject headers = new JSONObject(header);

        headers.keySet().forEach(keys -> {
            builder.header(keys, headers.getString(keys));
                }
        );

        if (!headers.has("Content-Type")) {
            return builder.url(caseDetailVO.getUrl()).get().build();
        }
//        if (headers.getString("Content-Type").equals("application/x-www-form-urlencoded")){
//            FormBody.Builder formBuilder = new FormBody.Builder();
//            if (caseDetailVO.getMethod().equals("POST")){
//                if (!StringUtils.isNullOrEmpty(data)){
//                    JSONObject body = new JSONObject(data);
//                    body.keySet().forEach(e->formBuilder.add(e,body.getString(e)));
//                }
//                FormBody body = formBuilder.build();
//                request = builder.url(url).post(body).build();
//            }else if (method.equals("GET")){
//                request = builder.url(url).build();
//            }
//        }

        if (headers.getString("Content-Type").contains("application/json")) {
            RequestBody body = RequestBody.create(data, JSON);
            switch (caseDetailVO.getMethod()) {
                case "POST":
                    request = builder.url(url).post(body).build();
                    break;
                case "PUT":
                    request = builder.url(url).put(body).build();
                    break;
                case "DELETE":
                    request = builder.url(url).delete(body).build();
                    break;
                default:
                    request = builder.url(url).build();
                    break;
            }
        }

        return request;
    }

    public String replaceParemeters(String string) {
        if (StringUtils.isNullOrEmpty(string)) {
            return string;
        }
        String str = "\\$\\{.*?}";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(string);
        while (m.find()) {
            //去全局变量map里查
            String newStr = m.group().replace("${", "").replace("}", "");
            string = string.replace(m.group(), userDefinParamMap.get(newStr));
        }
        return string.replaceAll("\r|\n", "");
    }

    private void extraction(CaseDetailVO caseDetailVO, String result) {
        String extract = caseDetailVO.getExtract();
        if (!StringUtils.isNullOrEmpty(extract)) {
            //获得存储变量名及变量的path
            JSONObject dependFields = new JSONObject(extract);
            Iterator<String> sIterator = dependFields.keys();
            while (sIterator.hasNext()) {
                // 获得变量名
                String variable = sIterator.next();
                // 获得变量path
                String variablePath = dependFields.getString(variable);
                // 从返回结果中获取变量值fasjson
//                String variableValue = JSONPath.eval(result, variablePath).toString();
                String variableValue = JSONPath.read(result,variablePath).toString();
                // 将获取的变量放到用于储存全局变量的map中
                userDefinParamMap.put(variable, variableValue);
                System.out.println("全局变量 :" + userDefinParamMap.toString());
            }
        }
    }

}


