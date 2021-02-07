//package cow.applications.context;
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import wewin.infrastructures.converter.WarehouseGoodsSkuConverter;
//import wewin.infrastructures.repository.WarehouseGoodsSkuRepository;
//import wewin.infrastructures.struct.ido.PageResultIDO;
//import wewin.infrastructures.struct.ido.WarehouseGoodsSkuIDO;
//import wewin.infrastructures.struct.ido.WarehouseGoodsSkuQueryIDO;
//import wewin.infrastructures.struct.vo.PageResultVO;
//import wewin.infrastructures.struct.vo.WarehouseGoodsSkuQueryVO;
//import wewin.infrastructures.struct.vo.WarehouseGoodsSkuVO;
//
//import java.util.List;
//
///**
// * @author napuping
// * @date 2020-12-10 下午7:09
// * @description
// */
//@Component
//public class WarehouseGoodsSkuContext {
//
//    private final WarehouseGoodsSkuConverter warehouseGoodsSkuConverter;
//    private final WarehouseGoodsSkuRepository warehouseGoodsSkuRepository;
//
//    public WarehouseGoodsSkuContext(WarehouseGoodsSkuConverter warehouseGoodsSkuConverter,
//                                    WarehouseGoodsSkuRepository warehouseGoodsSkuRepository) {
//        this.warehouseGoodsSkuConverter = warehouseGoodsSkuConverter;
//        this.warehouseGoodsSkuRepository = warehouseGoodsSkuRepository;
//    }
//
//    public PageResultIDO<WarehouseGoodsSkuIDO> search(String warehouseId, WarehouseGoodsSkuQueryIDO queryIDO) {
//        queryIDO.setWarehouseId(StringUtils.isEmpty(queryIDO.getWarehouseId()) ? warehouseId : queryIDO.getWarehouseId());
//        WarehouseGoodsSkuQueryVO warehouseGoodsSkuQueryVO = warehouseGoodsSkuConverter.toWarehouseGoodsSkuVO(queryIDO);
//        PageResultVO<WarehouseGoodsSkuVO> pageResultVO = warehouseGoodsSkuRepository.selectByExample(warehouseGoodsSkuQueryVO);
//        List<WarehouseGoodsSkuIDO> warehouseGoodsSkuIDOS = warehouseGoodsSkuConverter.toWarehouseGoodsSkuIDO(pageResultVO.getList());
//        return new PageResultIDO<>(warehouseGoodsSkuIDOS, pageResultVO.getCount());
//    }
//}
