package cow.infrastructures.util;//package com.util;
//
//import com.mysql.cj.util.StringUtils;
//import org.json.JSONObject;
//import com.config.CaseConfig;
//import com.struct.po.CaseDetailPO;
//
//import java.util.Iterator;
//
//public class ExtratorUtil {
//    //JSON提前器
//    static void extrator(CaseDetailPO caseDetailPO, String result){
//        String extract = caseDetailPO.getExtract();
//        if (!StringUtils.isNullOrEmpty(extract)) {
//            //获得存储变量名及变量的path
//            JSONObject dependFields = new JSONObject(extract);
//            Iterator<String> sIterator = dependFields.keys();
//            while(sIterator.hasNext()) {
//                // 获得变量名
//                String variable = sIterator.next();
//                // 获得变量path
//                String  variablePath= dependFields.getString(variable);
//                // 从返回结果中获取变量值fasjson
//                //String variableValue = JSONPath.eval(result, variablePath).toString();
//                String variableValue = com.jayway.jsonpath.JsonPath.read(result,variablePath);
//                // 将获取的变量放到用于储存全局变量的map中
//                CaseConfig.globalParam.put(variable,variableValue);
//                System.out.println("全局变量 :"+ CaseConfig.globalParam.toString());
//            }
//        }
//
//    }
//
//    /*
//    正则提前器
//     */
//    //TODO
//}
