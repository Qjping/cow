//package com.util;
//
//import com.mysql.cj.util.StringUtils;
//import com.config.CaseConfig;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
///**
// * 参数中如果包含变量，则从全局map中搜索变量进行替换
// * 如果不存在变量，则不做任何变动
// */
//public class ReplaceParemetersUtil {
//
//
//   public static String replaceParemeters(String string){
//        if(StringUtils.isNullOrEmpty(string)){
//            return string;
//        }
//        String str ="\\$\\{.*?}";
//        Pattern p = Pattern.compile(str);
//        Matcher m = p.matcher(string);
//        while (m.find()){
//            //去全局变量map里查
//            String newStr = m.group().replace("${","").replace("}","");
//            string = string.replace(m.group(), CaseConfig.globalParam.get(newStr));
//        }
//        return string;
//    }
//}
