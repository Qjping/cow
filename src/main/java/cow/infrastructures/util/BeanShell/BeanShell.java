package cow.infrastructures.util.BeanShell;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

@Slf4j
public class BeanShell {
    public static Map var;
    public static Map pros;

    public String getResponseDataAsString(){
        return null;
    }
    public String getResponseCode(){
        return null;
    }

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
}
