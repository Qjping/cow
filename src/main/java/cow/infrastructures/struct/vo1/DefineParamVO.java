package cow.infrastructures.struct.vo1;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * table name: user_define_param
 * author name: qiujingping
 * create time: 2021-02-07 15:11:15
 */
public class DefineParamVO {

    private Integer id;
    private Integer caseGroupId;
    private String userDefineParameters;
    private Integer deleteFlag;
    private LocalDate recordDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public void setId(Integer id){
        this.id=id;
    }

    public Integer getId(){
        return id;
    }

    public void setCaseGroupId(Integer caseGroupId){
        this.caseGroupId=caseGroupId;
    }

    public Integer getCaseGroupId(){
        return caseGroupId;
    }

    public void setUserDefineParameters(String userDefineParameters){
        this.userDefineParameters=userDefineParameters;
    }

    public String getUserDefineParameters(){
        return userDefineParameters;
    }

    public void setDeleteFlag(Integer deleteFlag){
        this.deleteFlag=deleteFlag;
    }

    public Integer getDeleteFlag(){
        return deleteFlag;
    }

    public void setRecordDate(LocalDate recordDate){
        this.recordDate=recordDate;
    }

    public LocalDate getRecordDate(){
        return recordDate;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy=createdBy;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedBy(String updatedBy){
        this.updatedBy=updatedBy;
    }

    public String getUpdatedBy(){
        return updatedBy;
    }

    @Override
    public String toString() {
        return "DefineParamVO{" +
            "id=" + id +
            ", caseGroupId=" + caseGroupId +
            ", userDefineParameters=" + userDefineParameters +
            ", deleteFlag=" + deleteFlag +
            ", recordDate=" + recordDate +
            ", createdAt=" + createdAt +
            ", createdBy='" + createdBy + '\'' +
            ", updatedAt=" + updatedAt +
            ", updatedBy='" + updatedBy + '\'' +
            "}";
    }
}

