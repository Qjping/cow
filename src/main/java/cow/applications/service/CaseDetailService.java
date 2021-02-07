package cow.applications.service;

import cow.infrastructures.converter.CaseDetailconverter;
import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.CaseDetailVO;
import cow.infrastructures.struct.vo1.CaseQueryVO;
import cow.infrastructures.struct.vo1.PageResultVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseDetailService {
    private final CaseDetailRepository caseDetailRepository;
    private final CaseDetailconverter caseDetailconverter;

    public CaseDetailService(CaseDetailRepository caseDetailRepository, CaseDetailconverter caseDetailconverter) {
        this.caseDetailRepository = caseDetailRepository;
        this.caseDetailconverter = caseDetailconverter;
    }

    public PageResultIDO<CaseQueryIDO> searchCaseDetailList(CaseQueryIDO caseQueryIDO){
        CaseQueryVO caseQueryVO=caseDetailconverter.voToIdo(caseQueryIDO);
        PageResultVO<CaseDetailVO> pageResultVO =caseDetailRepository.getCaseDetailListByCondition(caseQueryVO);
        List<CaseDetailVO> areaIDOList = pageResultVO.getList();

       return new PageResultIDO(areaIDOList,pageResultVO.getCount());

    }
}
