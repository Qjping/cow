package cow.infrastructures.struct.vo1;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * table name: user_account
 * author name: qiujingping
 * create time: 2021-02-07 15:11:15
 */
public class UserAccountVO {

    private String id;
    private String userId;
    private String account;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate recordDate;
    private String createdBy;
    private String salt;
    private Boolean status;

    public void setId(String id){
        this.id=id;
    }

    public String getId(){
        return id;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setAccount(String account){
        this.account=account;
    }

    public String getAccount(){
        return account;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return password;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setRecordDate(LocalDate recordDate){
        this.recordDate=recordDate;
    }

    public LocalDate getRecordDate(){
        return recordDate;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy=createdBy;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public void setSalt(String salt){
        this.salt=salt;
    }

    public String getSalt(){
        return salt;
    }

    public void setStatus(Boolean status){
        this.status=status;
    }

    public Boolean getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "AccountVO{" +
            "id='" + id + '\'' +
            ", userId='" + userId + '\'' +
            ", account='" + account + '\'' +
            ", password='" + password + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", recordDate=" + recordDate +
            ", createdBy='" + createdBy + '\'' +
            ", salt='" + salt + '\'' +
            ", status=" + status +
            "}";
    }
}

