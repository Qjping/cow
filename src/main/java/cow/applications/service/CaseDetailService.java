package cow.applications.service;

import com.mysql.cj.util.StringUtils;
import cow.infrastructures.converter.CaseDetailconverter;


import cow.infrastructures.jooq.tables.CaseResult;
import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.CaseDetailAddVO;
import cow.infrastructures.struct.vo1.CaseDetailVO;
import cow.infrastructures.struct.vo1.CaseQueryVO;
import cow.infrastructures.struct.vo1.PageResultVO;

import io.netty.handler.codec.json.JsonObjectDecoder;
import okhttp3.*;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CaseDetailService {
    private final CaseDetailRepository caseDetailRepository;
    private final CaseDetailconverter caseDetailconverter;
    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public CaseDetailService(CaseDetailRepository caseDetailRepository, CaseDetailconverter caseDetailconverter) {
        this.caseDetailRepository = caseDetailRepository;
        this.caseDetailconverter = caseDetailconverter;
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
        List<CaseResult> caseResultList = new ArrayList<CaseResult>();
        //发送请求
        caseDetailVOS.forEach(caseDetailVO -> {
                    try {
                        Response response = client.newCall(request(caseDetailVO)).execute();
                        String responseBody = response.body().string();
                        Integer code = response.code();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

    }

    public Request request(CaseDetailVO caseDetailVO){
        Request.Builder builder = new Request.Builder();
        Request request = null;
        String header = caseDetailVO.getHeader();
        String url =caseDetailVO.getUrl();
        String data= caseDetailVO.getData();
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
            RequestBody body = RequestBody.create(JSON, data);
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


