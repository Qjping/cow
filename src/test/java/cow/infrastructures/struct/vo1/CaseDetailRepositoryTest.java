package cow.infrastructures.struct.vo1;

import cow.CowApplication;

import cow.applications.service.CaseDetailService;
import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.repository.CaseDetailRepository;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CaseDetailRepositoryTest  {
    @Autowired
    CaseDetailRepository caseDetailRepository;
    @Autowired
    CaseDetailService caseDetailService;

    @Test
    void getAreaListByCondition() {
        CaseQueryIDO caseQueryIDO= new CaseQueryIDO();

        System.out.println(  caseDetailService.searchCaseDetailList(caseQueryIDO).toString());
    }

    @Test
    void addCase() {
        CaseDetailAddVO caseDetailAddVO = new CaseDetailAddVO();
        caseDetailAddVO.setUrl("http;//www.baidu.com");
        caseDetailAddVO.setSort(2);
        List<CaseDetailAddVO> caseDetailAddVOList = new ArrayList<CaseDetailAddVO>();

        caseDetailRepository.addCase(caseDetailAddVO);
    }
}
