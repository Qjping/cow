package cow.infrastructures.util;//package com.util;
//
//import org.json.JSONObject;
//import org.testng.Assert;
//import com.config.CaseConfig;
//
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class AssertUtil {
//
//    //json路径校验
//    static void check(JSONObject expecteds, String resultResponce) throws IOException {
//        Iterator<String> expectedIterator = expecteds.keys();
//        System.out.println(resultResponce);
//        while (expectedIterator.hasNext()){
//            String path = expectedIterator.next();
//            System.out.println(path);
//            String expected = expecteds.getString(path);
//            String regex = "\\$\\{(.*?)\\}";
//            Pattern p = Pattern.compile(regex);
//            Matcher m = p.matcher(expected);
//
//            if (m.find()) {
//                expected = expected.replace("${","").replace("}","");
//                Assert.assertEquals(CaseConfig.globalParam.get(expected), expecteds);
//
//            } else {
//                System.out.println(expecteds.getString(path));
//                Assert.assertEquals(expecteds.getString(path), CaseConfig.globalParam.get(expected));
//            }
//        }
//
//    }
//
//    //正则校验
//    private static void check(String regx, String resultResponce) throws IOException {
//
//    }
//}
