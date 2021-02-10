package cow.applications.service;

import com.mysql.cj.util.StringUtils;
import com.mysql.cj.util.Util;
import cow.infrastructures.converter.CaseDetailconverter;


import cow.infrastructures.jooq.tables.CaseResult;
import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.repository.CaseResultRepository;
import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.*;

import okhttp3.*;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CaseDetailService {
    private final CaseDetailRepository caseDetailRepository;
    private final CaseDetailconverter caseDetailconverter;
    private final OkHttpClient client = new OkHttpClient();
    private final CaseResultRepository caseResultRepository;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public CaseDetailService(CaseDetailRepository caseDetailRepository, CaseDetailconverter caseDetailconverter, CaseResultRepository caseResultRepository) {
        this.caseDetailRepository = caseDetailRepository;
        this.caseDetailconverter = caseDetailconverter;
        this.caseResultRepository = caseResultRepository;
    }

    public PageResultIDO<CaseQueryIDO> searchCaseDetailList(CaseQueryIDO caseQueryIDO){
        CaseQueryVO caseQueryVO=caseDetailconverter.caseQueryIdoTovo(caseQueryIDO);
        PageResultVO<CaseDetailVO> pageResultVO =caseDetailRepository.getCaseDetailListByCondition(caseQueryVO);
        List<CaseDetailVO> caseDetailVOS = pageResultVO.getList();

       return new PageResultIDO(caseDetailVOS,pageResultVO.getCount());

    }


    @Transactional
    public void addCase(CaseDetailAddIDO caseQueryIDO){
        CaseDetailAddVO caseDetailAddVO=caseDetailconverter.caseDetailidoTovo(caseQueryIDO);
        caseDetailRepository.addCase(caseDetailAddVO);
    }

    //TODO 替换参数，保存结果，
    public void excute(CaseQueryIDO caseQueryIDO){
        CaseQueryVO caseQueryVO=caseDetailconverter.caseQueryIdoTovo(caseQueryIDO);
        PageResultVO<CaseDetailVO> pageResultVO =caseDetailRepository.getCaseDetailListByCondition(caseQueryVO);
        List<CaseDetailVO> caseDetailVOS = pageResultVO.getList();
        List<CaseResultVO> caseResultList = new ArrayList<CaseResultVO>();
        //发送请求
        caseDetailVOS.forEach(caseDetailVO -> {
                    try {
                        Response response = client.newCall(request(caseDetailVO)).execute();

                        Integer code = response.code();
                        Headers headers = response.headers();
                        CaseResultVO caseResultVO = new CaseResultVO();
                        caseResultVO.setUrl(response.request().url().toString());
                        if(response.request().body()!=null){
                            caseResultVO.setData(response.request().body().toString());
                        }
                        caseResultVO.setResponceResult(response.body().string());
                        caseResultVO.setCaseGroupId(caseDetailVO.getGroupId());
                        caseResultVO.setCaseId(caseDetailVO.getId());
                        caseResultList.add(caseResultVO);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        caseResultRepository.save(caseResultList);

    }

    public Request request(CaseDetailVO caseDetailVO){
        Request.Builder builder = new Request.Builder();
        Request request = null;
        String header = caseDetailVO.getHeader();
        String url =caseDetailVO.getUrl();
        String data= caseDetailVO.getData();
        if(StringUtils.isNullOrEmpty(header)){
            return  builder.url(caseDetailVO.getUrl()).get().build();
        }
        JSONObject headers = new JSONObject(header);
        headers.keySet().forEach(keys->{
            builder.header(keys,headers.getString(keys));
                }
        );

        if(!headers.has("Content-Type")){
            return  builder.url(caseDetailVO.getUrl()).get().build();
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

        if (headers.getString("Content-Type").contains("application/json")){
            RequestBody body = RequestBody.create(data, JSON);
            switch (caseDetailVO.getMethod()) {
                case "POST": request = builder.url(url).post(body).build();break;
                case "PUT": request = builder.url(url).put(body).build();break;
                case "DELETE": request = builder.url(url).delete(body).build();break;
                default: request = builder.url(url).build();break;
            }
        }

        return  request;
    }

    public static String replaceParemeters(String string){
        if(StringUtils.isNullOrEmpty(string)){
            return string;
        }
        String str ="\\$\\{.*?}";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(string);
        while (m.find()){
            //去全局变量map里查
            String newStr = m.group().replace("${","").replace("}","");
//            string = string.replace(m.group(), CaseConfig.globalParam.get(newStr));
        }
        return string;
    }


}


