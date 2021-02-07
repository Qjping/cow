package cow.infrastructures.converter;

import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.vo1.CaseQueryVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CaseDetailconverter {
    CaseQueryVO voToIdo(CaseQueryIDO caseQueryIDO);
}
