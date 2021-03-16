package cow.infrastructures.struct.vo1;

import cow.infrastructures.struct.dto.AssertResultDTO;
import cow.infrastructures.struct.dto.ExtractResultDTO;
import cow.infrastructures.struct.ido.AssertResultIDO;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * table name: case_result
 * author name: qiujingping
 * create time: 2021-02-07 15:10:31
 */
@Data
public class CaseResultVO {

    private String id;
    private Integer caseGroupId;
    private Integer caseId;
    private String url;
    private String path;
    private String header;
    private String method;
    private String data;
    private String  responseResult;
    private List<AssertResultDTO> assertResult;
    private List<ExtractResultDTO> extractResult;
    private String userDefineParameters;
    private String deleteFlag;
    private LocalDate recordDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Integer HttpStatusCode;
}

