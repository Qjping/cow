package cow.infrastructures.converter;

import cow.infrastructures.jooq.tables.CaseGroup;
import cow.infrastructures.struct.ido.CaseGroupIDO;
import cow.infrastructures.struct.ido.CaseGroupQueryIDO;
import cow.infrastructures.struct.vo1.CaseGroupQueryVO;
import cow.infrastructures.struct.vo1.CaseGroupVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface CaseGroupConverter {
    List<CaseGroupIDO> VoToListIDOList(List<CaseGroupVO> caseGroupVOList);
    CaseGroupQueryVO IdoToQueryVO(CaseGroupQueryIDO queryIDO);
}
