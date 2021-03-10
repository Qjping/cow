package cow.infrastructures.struct.ido;

import lombok.Data;

import java.util.List;

@Data
public class CaseReportIDO {
    public List<CaseResultIDO> caseResultIDOList;
    public Integer passCount;
    public Integer failCount;
    public Integer doAssertPassCount;
    public Integer doAssertFailCount;

}
