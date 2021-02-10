package cow.infrastructures.struct.vo1;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * table name: case_result
 * author name: qiujingping
 * create time: 2021-02-07 15:10:31
 */
public class CaseResultVO {

    private String id;
    private Integer caseGroupId;
    private Integer caseId;
    private String url;
    private String path;
    private String header;
    private String method;
    private String data;
    private String  responceResult;
    private String  assertResult;
    private String extractResult;
    private String userDefineParameters;
    private String deleteFlag;
    private LocalDate recordDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public void setId(String id){
        this.id=id;
    }

    public String getId(){
        return id;
    }

    public void setCaseGroupId(Integer caseGroupId){
        this.caseGroupId=caseGroupId;
    }

    public Integer getCaseGroupId(){
        return caseGroupId;
    }

    public void setCaseId(Integer caseId){
        this.caseId=caseId;
    }

    public Integer getCaseId(){
        return caseId;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public String getUrl(){
        return url;
    }

    public void setPath(String path){
        this.path=path;
    }

    public String getPath(){
        return path;
    }

    public void setHeader(String header){
        this.header=header;
    }

    public String getHeader(){
        return header;
    }

    public void setMethod(String method){
        this.method=method;
    }

    public String getMethod(){
        return method;
    }

    public void setData(String data){
        this.data=data;
    }

    public String getData(){
        return data;
    }

    public void setResponceResult(String responceResult){
        this.responceResult=responceResult;
    }

    public String getResponceResult(){
        return responceResult;
    }

    public void setAssertResult(String assertResult){
        this.assertResult=assertResult;
    }

    public String getAssertResult(){
        return assertResult;
    }

    public void setExtractResult(String extractResult){
        this.extractResult=extractResult;
    }

    public String getExtractResult(){
        return extractResult;
    }

    public void setUserDefineParameters(String userDefineParameters){
        this.userDefineParameters=userDefineParameters;
    }

    public String getUserDefineParameters(){
        return userDefineParameters;
    }

    public void setDeleteFlag(String deleteFlag){
        this.deleteFlag=deleteFlag;
    }

    public String getDeleteFlag(){
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
        return "ResultVO{" +
            "id=" + id +
            ", caseGroupId=" + caseGroupId +
            ", caseId=" + caseId +
            ", url='" + url + '\'' +
            ", path='" + path + '\'' +
            ", header='" + header + '\'' +
            ", method='" + method + '\'' +
            ", data='" + data + '\'' +
            ", responceResult=" + responceResult +
            ", assertResult=" + assertResult +
            ", extractResult=" + extractResult +
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

