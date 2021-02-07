package cow.infrastructures.struct.vo1;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.sql.*;

/**
 * table name: case_config
 * author name: qiujingping
 * create time: 2021-02-07 15:10:31
 */ 
public class ConfigVO {

    private Integer id;
    private String url;
    private String headers;
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

    public void setHeaders(String headers){
        this.headers=headers;
    }

    public String getHeaders(){
        return headers;
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
        return "ConfigVO{" + 
            "id=" + id + 
            ", url='" + url + '\'' + 
            ", headers='" + headers + '\'' + 
            ", deleteFlag=" + deleteFlag + 
            ", recordDate=" + recordDate + 
            ", createdAt=" + createdAt + 
            ", createdBy='" + createdBy + '\'' + 
            ", updatedAt=" + updatedAt + 
            ", updatedBy='" + updatedBy + '\'' + 
            "}";
    }
}

