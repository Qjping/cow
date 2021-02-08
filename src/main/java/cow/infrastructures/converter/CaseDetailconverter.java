package cow.infrastructures.converter;

import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.vo1.CaseDetailAddVO;
import cow.infrastructures.struct.vo1.CaseDetailVO;
import cow.infrastructures.struct.vo1.CaseQueryVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CaseDetailconverter {
    CaseQueryVO caseQueryIdoTovo(CaseQueryIDO caseQueryIDO);

    CaseDetailAddVO caseDetailidoTovo(CaseDetailAddIDO caseDetailAddIDO);
}
