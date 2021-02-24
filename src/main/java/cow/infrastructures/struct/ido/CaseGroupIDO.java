package cow.infrastructures.struct.ido;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class CaseGroupIDO {
    private String id;
    private String description;
    private LocalDate recordDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
