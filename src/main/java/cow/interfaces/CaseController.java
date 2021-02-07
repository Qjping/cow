package cow.interfaces;

import cow.infrastructures.enumeration.ExceptionCodeEnum;
import cow.infrastructures.jooq.tables.CaseDetail;
import cow.infrastructures.struct.ido.ApiResultIDO;
import cow.infrastructures.struct.ido.CaseQueryIDO;

import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.CaseDetailVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;



@RestController
@RequestMapping("/case")
public class CaseController {

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

}
