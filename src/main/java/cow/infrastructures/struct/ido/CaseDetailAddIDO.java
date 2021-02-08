package cow.infrastructures.struct.ido;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalDate;


/**
 * table name: case_detail
 * author name: qiujingping
 * create time: 2021-02-07 15:10:31
 */
@Data
public class CaseDetailAddIDO {

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
    private String createdBy;
    private String updatedBy;

}

