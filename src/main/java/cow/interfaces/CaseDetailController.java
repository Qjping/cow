package cow.interfaces;

import cow.applications.service.CaseDetailService;
import cow.infrastructures.struct.ido.*;
import cow.infrastructures.struct.vo1.CaseDetailVO;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseDetailController {
    private final CaseDetailService caseDetailService;

    private final TransactionTemplate transactionTemplate;

    public CaseDetailController(CaseDetailService caseDetailService, TransactionTemplate transactionTemplate) {
        this.caseDetailService = caseDetailService;
        this.transactionTemplate = transactionTemplate;
    }

    @GetMapping("search")
    public ApiResultIDO<PageResultIDO<CaseDetailIDO>> search(CaseQueryIDO caseQueryIDO){
        return ApiResultIDO.success(caseDetailService.searchCaseDetailList(caseQueryIDO));
    }

    @PostMapping("add")
    public ApiResultIDO<Void> add(@RequestBody List<CaseDetailAddIDO> caseDetailAddIDO){
        caseDetailService.addCase(caseDetailAddIDO);
        return ApiResultIDO.success();
    }


    @GetMapping("execute")
    public ApiResultIDO<PageResultIDO<Void>> execute(CaseQueryIDO caseQueryIDO){
        caseDetailService.execute(caseQueryIDO);
        return ApiResultIDO.success();
    }


}

