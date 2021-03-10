package cow.infrastructures.converter;

import cow.infrastructures.jooq.tables.CaseDetail;
import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseDetailIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.CaseResultIDO;
import cow.infrastructures.struct.vo1.CaseDetailAddVO;
import cow.infrastructures.struct.vo1.CaseDetailVO;
import cow.infrastructures.struct.vo1.CaseQueryVO;
import cow.infrastructures.struct.vo1.CaseResultVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CaseConverter {
    CaseQueryVO caseQueryIdoToVo(CaseQueryIDO caseQueryIDO);

    CaseDetailAddVO caseDetailIdoToVo(CaseDetailAddIDO caseDetailAddIDO);

    List<CaseDetailAddVO> caseDetailIdoListToVoList( List<CaseDetailAddIDO> caseDetailAddIDO);

    List<CaseDetailIDO> caseDetailVoListToIDO(List<CaseDetailVO> caseDetailVOList);

    List<CaseResultIDO> caseResultVoToIDO(List<CaseResultVO> caseResultVOS);
}
