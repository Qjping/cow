package cow.interfaces;

import cow.applications.service.CaseDetailService;
import cow.infrastructures.struct.ido.ApiResultIDO;
import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case")
public class CaseDetailCrontroller {
    private final CaseDetailService caseDetailService;

    private final TransactionTemplate transactionTemplate;

    public CaseDetailCrontroller(CaseDetailService caseDetailService, TransactionTemplate transactionTemplate) {
        this.caseDetailService = caseDetailService;
        this.transactionTemplate = transactionTemplate;
    }

    @GetMapping("search")
    public ApiResultIDO<PageResultIDO<CaseQueryIDO>> seach(CaseQueryIDO caseQueryIDO){
        return ApiResultIDO.success(caseDetailService.searchCaseDetailList(caseQueryIDO));
    }
    @GetMapping("add")
    public ApiResultIDO<Void> add(CaseDetailAddIDO caseDetailAddIDO){
        caseDetailService.addCase(caseDetailAddIDO);
        return ApiResultIDO.success();
    }


    @GetMapping("excute")
    public ApiResultIDO<PageResultIDO<Void>> excute(CaseQueryIDO caseQueryIDO){
        caseDetailService.excute(caseQueryIDO);
        return null;
    }


}

