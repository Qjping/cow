package cow.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("request")
public class CaseConfig {
    public Integer isfailRetry;
    public Integer retryCount;
    public HashMap<String,String> userDefineParams = new HashMap<>();
}
