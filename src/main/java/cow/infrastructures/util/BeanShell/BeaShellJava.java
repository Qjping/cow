package cow.infrastructures.util.BeanShell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.TimeUnit;

public class BeaShellJava {

    public void excute(String Java) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {



        OutputStream os = new FileOutputStream("DynaScript.java");

        os.write(Java.getBytes());
        os.close();

        String[] compileArgs = new String[] {"DynaScript.java"};
        com.sun.tools.javac.Main.compile(compileArgs);


        URLClassLoader loader =
                new URLClassLoader(new URL[]{new File(".").toURI().toURL()});
        Class<?> scriptClass = loader.loadClass("DynaScript");


        Object obj = scriptClass.newInstance();
        scriptClass.getDeclaredField("aliasName").set(obj, "alias");
        scriptClass.getDeclaredField("originalName").set(obj, "name");

        scriptClass.getDeclaredMethod("excute").invoke(obj);
        String displayName = (String)scriptClass.getDeclaredField("displayName").get(obj);

        System.out.println(displayName);

    }


    public boolean waitFor(long timeout, TimeUnit unit, Process process)
            throws InterruptedException
    {
        long startTime = System.nanoTime();
        long rem = unit.toNanos(timeout);

        do {
            try {
                //如果脚本没执行完 就会产生IllegalThreadStateException通过抓取异常来判断是否执行成功
                process.exitValue();
                return true;
            } catch(IllegalThreadStateException ex) {
                if (rem > 0)
                    Thread.sleep(
                            Math.min(TimeUnit.NANOSECONDS.toMillis(rem) + 1, 100));
            }
            rem = unit.toNanos(timeout) - (System.nanoTime() - startTime);
        } while (rem > 0);
        return false;
    }


    //    public void run (){
//        //初始化脚本命令
//        String commond = "";
//        Process process = null;
//        //脚本执行返回值
//        boolean flag = false;
//        try {
//            //开始执行脚本
//            process = Runtime.getRuntime().exec(commond);
//            //这是jdk1.8的waitFor方法，带有时间戳防止阻塞
//            if(this.waitFor(1, TimeUnit.MINUTES, process)) {
//                //获取执行错误流
//                InputStream errin = process.getErrorStream();
//                //获取响应流
//                InputStream in = process.getInputStream();
//                StringBuilder errBuilder = new StringBuilder();
//                while(errin.available() > 0) {
//                    Character c = new Character((char)errin.read());
//                    errBuilder.append(c);
//                }
//                StringBuilder inBuilder = new StringBuilder();
//                while(in.available() > 0) {
//                    Character c = new Character((char)in.read());
//                    inBuilder.append(c);
//                }
//                //判断脚本返回流中是否成功  这部分取决于自定义脚本返回的内容
//                if(!inBuilder.toString().contains("Success") && inBuilder.toString().contains("failed")) {
//                    flag = true;
//                }
//                //获取脚本执行结果
//                int i = 0;
//                loop : while(i < 3) {
//                    Thread.sleep(1000);
//                    //0表示正常执行结束
//                    int code = process.exitValue();
//                    if(code == 0) {
//                        break loop;
//                    } else {
//                        i++;
//                    }
//                }
//                if(i >= 3) {
//                    alarmLogger.error("调用脚本失败 请检查脚本配置");
//                    ar.setResultCode(-6);
//                    ar.setResultMessage("调用脚本发生异常");
//                    ar.setResultFlag(false);
//                }
//                if(flag) {
//                    //这是调用脚本成功 脚本中校验的东西失败的结果
//                    ar.setResultCode(5);
//                    ar.setResultMessage("具体业务失败");
//                    ar.setResultFlag(false);
//                }
//            } else {
//                alarmLogger.error("调用脚本超时异常");
//                ar.setResultCode(-5);
//                ar.setResultMessage("调用脚本超时异常");
//                ar.setResultFlag(false);
//            }
//        } catch (Exception e) {
//            alarmLogger.error("调用脚本发生异常");
//            ar.setResultCode(-5);
//            ar.setResultMessage("调用脚本发生异常");
//            ar.setResultFlag(false);
//        } finally {
//            if(process != null) {
//                process.destroy();
//            }
//        }
//    }
}
