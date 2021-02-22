package cow.infrastructures.struct.ido;



import javax.validation.constraints.NotEmpty;

/**
 * @author napuping
 * @date 2020-11-03 下午7:29
 * @description
 */

public class LoginQueryIDO {

    private String type;
    @NotEmpty(message = "帐号不能为空")

    private String account;
    @NotEmpty(message = "密码不能为空")

    private String loginCode;


    private String deviceType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "LoginUserQueryIDO{" +
                "type='" + type + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
