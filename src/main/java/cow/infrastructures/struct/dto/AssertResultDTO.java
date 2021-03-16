package cow.infrastructures.struct.dto;

import lombok.Data;

@Data
public class AssertResultDTO {
    public Boolean isPass;
    public String failCause;
}
