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
        try {
            SpringApplication.run(CowApplication.class, args);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
