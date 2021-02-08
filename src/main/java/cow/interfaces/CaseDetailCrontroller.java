package cow.interfaces;

import cow.applications.service.CaseDetailService;
import cow.infrastructures.jooq.tables.CaseDetail;
import cow.infrastructures.struct.ido.ApiResultIDO;
import cow.infrastructures.struct.ido.CaseDetailAddIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case")
public class CaseDetailCrontroller {
    private final CaseDetailService caseDetailService;

    public CaseDetailCrontroller(CaseDetailService caseDetailService) {
        this.caseDetailService = caseDetailService;
    }

    @GetMapping("search")
    public ApiResultIDO<PageResultIDO<CaseQueryIDO>> seach(CaseQueryIDO caseQueryIDO){
        return ApiResultIDO.success(caseDetailService.searchCaseDetailList(caseQueryIDO));
    }
//    @GetMapping("add")
//    public ApiResultIDO<PageResultIDO<Void>> add(CaseDetailAddIDO caseDetailAddIDO){
//        caseDetailService.addCase(caseDetailAddIDO);
//        return ApiResultIDO.success(caseDetailService.searchCaseDetailList(caseQueryIDO));
//    }


//    @GetMapping("excute")
//    public ApiResultIDO<PageResultIDO<Void>> excute(CaseDetailAddIDO caseDetailAddIDO){
//        caseDetailService.addCase(caseDetailAddIDO);
//        return ApiResultIDO.success(caseDetailService.searchCaseDetailList(caseQueryIDO));
//    }


}

