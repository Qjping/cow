package cow;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CowApplication {

    public static void main(String[] args) throws IOException {
//         OkHttpClient client = new OkHttpClient();
//        Request.Builder builder = new Request.Builder();
//        Request request = null;
//
//        Response execute = client.newCall(builder.url("https://www.baidu.com").get().build()).execute();
//        System.out.println(execute.body().string());

        SpringApplication.run(CowApplication.class, args);
    }

}
