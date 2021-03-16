package cow.infrastructures.struct.ido;

import cow.infrastructures.struct.dto.AssertResultDTO;
import cow.infrastructures.struct.dto.ExtractResultDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class CaseResultIDO {

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
    private Integer httpStatusCode;
}
