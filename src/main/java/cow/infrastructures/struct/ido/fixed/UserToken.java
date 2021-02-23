package cow.infrastructures.struct.ido.fixed;

import java.io.Serializable;

public class UserToken implements Serializable {
    private String userId;
    private String name;
    private String phone;
    private String headUrl;
    private String jobNumber;
    private String accessToken;
    private Integer superFlag;

    public UserToken() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadUrl() {
        return this.headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getSuperFlag() {
        return this.superFlag;
    }

    public void setSuperFlag(Integer superFlag) {
        this.superFlag = superFlag;
    }

    public String toString() {
        return "UserToken{userId='" + this.userId + '\'' + ", name='" + this.name + '\'' + ", phone='" + this.phone + '\'' + ", headUrl='" + this.headUrl + '\'' + ", jobNumber='" + this.jobNumber + '\'' + ", accessToken='" + this.accessToken + '\'' + ", superFlag=" + this.superFlag + '}';
    }
}
