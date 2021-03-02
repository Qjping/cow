package cow.interfaces;

import cow.applications.service.CaseDetailService;
import cow.applications.service.CaseGroupService;
import cow.infrastructures.struct.ido.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/case-group")
public class CaseGroupController {
    private final CaseGroupService caseGroupService;
    private final CaseDetailService caseDetailService;

    public CaseGroupController(CaseGroupService caseGroupService, CaseDetailService caseDetailService) {
        this.caseGroupService = caseGroupService;
        this.caseDetailService = caseDetailService;
    }

//    private final WarehouseGoodsSkuContext warehouseGoodsSkuContext;
//
//    public WarehouseGoodsSkuController(WarehouseGoodsSkuContext warehouseGoodsSkuContext) {
//        this.warehouseGoodsSkuContext = warehouseGoodsSkuContext;
//    }
//
//
//    @GetMapping("search")
//    public ApiResultIDO<PageResultIDO<CaseDetailVO>> search(@Valid CaseQueryIDO queryIDO,
//                                                            BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            FieldError fieldError = (FieldError) bindingResult.getAllErrors().get(0);
//            return new ApiResultIDO(ExceptionCodeEnum.ARGUMENT_NOT_VALID_EXCEPTION.getCode(),
//                    fieldError.getDefaultMessage(), null);
//        }
//        PageResultIDO<CaseDetailVO> pageResultIDO = warehouseGoodsSkuContext.search(queryIDO.getWarehouseId(), queryIDO);
//        return ApiResultIDO.success(pageResultIDO);
//    }

    @GetMapping("search")
    public ApiResultIDO<PageResultIDO<CaseGroupIDO>> search(CaseGroupQueryIDO caseQueryIDO){
        return ApiResultIDO.success(caseGroupService.search(caseQueryIDO));
    }
    @PostMapping("execute")
    public ApiResultIDO<PageResultIDO<Void>> execute(CaseQueryIDO caseQueryIDO){
        caseDetailService.execute(caseQueryIDO);
        return ApiResultIDO.success();
    }

}
