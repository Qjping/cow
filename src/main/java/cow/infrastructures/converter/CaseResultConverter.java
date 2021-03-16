package cow.infrastructures.converter;

import cow.infrastructures.struct.ido.CaseResultIDO;
import cow.infrastructures.struct.vo1.CaseResultVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface CaseResultConverter {

    CaseResultIDO caseResultVoToIDO(CaseResultVO caseResultVO);
    List<CaseResultIDO> caseResultVoToIDO(List<CaseResultVO> caseResultVOS);
}
