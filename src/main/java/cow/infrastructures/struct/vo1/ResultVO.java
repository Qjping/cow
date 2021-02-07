package cow.infrastructures.struct.vo1;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.sql.*;

/**
 * table name: case_result
 * author name: qiujingping
 * create time: 2021-02-07 15:10:31
 */
public class ResultVO {

    private Integer id;
    private Integer caseGroupId;
    private Integer caseId;
    private String url;
    private String path;
    private String header;
    private String method;
    private String data;
    private Integer responceResult;
    private Integer assertResult;
    private Integer extractResult;
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

    public void setResponceResult(Integer responceResult){
        this.responceResult=responceResult;
    }

    public Integer getResponceResult(){
        return responceResult;
    }

    public void setAssertResult(Integer assertResult){
        this.assertResult=assertResult;
    }

    public Integer getAssertResult(){
        return assertResult;
    }

    public void setExtractResult(Integer extractResult){
        this.extractResult=extractResult;
    }

    public Integer getExtractResult(){
        return extractResult;
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

