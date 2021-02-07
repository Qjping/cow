package cow.infrastructures.struct.vo1;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.sql.*;

/**
 * table name: case_detail
 * author name: qiujingping
 * create time: 2021-02-07 15:10:31
 */
public class CaseDetailAddVO {

    private Integer id;
    private String url;
    private String path;
    private String header;
    private String method;
    private String data;
    private String assertions;
    private String extract;
    private String description;
    private String groupId;
    private Integer sort;
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

    public void setAssertions(String assertions){
        this.assertions=assertions;
    }

    public String getAssertions(){
        return assertions;
    }

    public void setExtract(String extract){
        this.extract=extract;
    }

    public String getExtract(){
        return extract;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setGroupId(String groupId){
        this.groupId=groupId;
    }

    public String getGroupId(){
        return groupId;
    }

    public void setSort(Integer sort){
        this.sort=sort;
    }

    public Integer getSort(){
        return sort;
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
        return "DetailVO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", header='" + header + '\'' +
                ", method='" + method + '\'' +
                ", data='" + data + '\'' +
                ", assertions='" + assertions + '\'' +
                ", extract='" + extract + '\'' +
                ", description='" + description + '\'' +
                ", groupId='" + groupId + '\'' +
                ", sort=" + sort +
                ", deleteFlag=" + deleteFlag +
                ", recordDate=" + recordDate +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                "}";
    }
}

