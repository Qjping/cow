package cow.infrastructures.struct.vo1;

import lombok.Data;

@Data
public class CaseGroupQueryVO {
    private String description;
    private Integer page = 1;
    private Integer limit = 10;
}
