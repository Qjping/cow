package cow.infrastructures.struct.ido;



import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author napuping
 * @date 2020-11-03 下午7:29
 * @description
 */
@Data
public class LoginQueryIDO {

    @NotEmpty(message = "帐号不能为空")
    private String account;
    @NotEmpty(message = "密码不能为空")
    private String loginCode;


}
