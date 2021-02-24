package cow.applications.service;

import cow.infrastructures.converter.CaseGroupConverter;
import cow.infrastructures.jooq.tables.CaseGroup;
import cow.infrastructures.repository.CaseGroupRepository;
import cow.infrastructures.struct.ido.CaseGroupIDO;
import cow.infrastructures.struct.ido.CaseGroupQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.CaseGroupQueryVO;
import cow.infrastructures.struct.vo1.CaseGroupVO;
import cow.infrastructures.struct.vo1.PageResultVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseGroupService {
    private final CaseGroupRepository caseGroupRepository;
    private final CaseGroupConverter caseGroupConverter;

    public CaseGroupService(CaseGroupRepository caseGroupRepository, CaseGroupConverter caseGroupConverter) {
        this.caseGroupRepository = caseGroupRepository;
        this.caseGroupConverter = caseGroupConverter;
    }

    public PageResultIDO<CaseGroupIDO> search(CaseGroupQueryIDO caseGroupQueryIDO){
        CaseGroupQueryVO caseGroupVO = caseGroupConverter.IdoToQueryVO(caseGroupQueryIDO);
      PageResultVO<CaseGroupVO> caseGroupVOPageResultVO = caseGroupRepository.search(caseGroupVO);
      List<CaseGroupVO> caseGroupIDOList= caseGroupVOPageResultVO.getList();
      return new PageResultIDO<>(caseGroupConverter.VoToListIDOList(caseGroupIDOList),caseGroupVOPageResultVO.getCount());
    }
}
