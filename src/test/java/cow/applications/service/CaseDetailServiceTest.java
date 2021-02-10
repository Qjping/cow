package cow.applications.service;

import cow.infrastructures.struct.ido.CaseQueryIDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CaseDetailServiceTest {
    @Autowired
    CaseDetailService caseDetailService;

    @Test
    public void excute(){
        CaseQueryIDO caseQueryIDO = new CaseQueryIDO();
        caseDetailService.excute(caseQueryIDO);
    }



}
