package cow.infrastructures.struct.ido;

import lombok.Data;

@Data
public class AssertResultIDO{
    public Boolean isPass;
    public String failCause;
}
