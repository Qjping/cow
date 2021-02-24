package cow.infrastructures.struct.ido;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class CaseDetailIDO {

    private Integer id;
    private String url;
    private String path;
    private String header;
    private String method;
    private String data;
    private String assertions;
    private String extract;
    private String description;
    private Integer groupId;
    private Integer sort;
    private Integer deleteFlag;
    private LocalDate recordDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
