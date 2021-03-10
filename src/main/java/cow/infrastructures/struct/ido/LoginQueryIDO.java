package cow.infrastructures.struct.ido;



import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 *
 */
@Data
public class LoginQueryIDO {

    @NotEmpty(message = "帐号不能为空")
    @JsonProperty("username")
    private String account;
    @NotEmpty(message = "密码不能为空")
    private String password;


}
