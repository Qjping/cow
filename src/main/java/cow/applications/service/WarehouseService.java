//package cow.applications.service;
//
//import org.springframework.stereotype.Service;
//import wewin.infrastructures.converter.WarehouseConverter;
//import wewin.infrastructures.enumeration.WarehouseTypeEnum;
//import wewin.infrastructures.repository.WarehouseRepository;
//import wewin.infrastructures.struct.vo.PageResultVO;
//import wewin.infrastructures.struct.vo.WarehouseQueryVO;
//import wewin.infrastructures.struct.vo.WarehouseVO;
//
//
//@Service
//public class WarehouseService {
//    private final WarehouseRepository wareHouseRepository;
//    private final WarehouseConverter warehouseConverter;
//
//    public WarehouseService(WarehouseRepository wareHouseRepository, WarehouseConverter warehouseConverter) {
//        this.wareHouseRepository = wareHouseRepository;
//        this.warehouseConverter = warehouseConverter;
//    }
//
//
//    public PageResultVO<WarehouseVO> searchWareHouseList(WarehouseQueryVO warehouseQueryVO) {
//        PageResultVO<WarehouseVO> pageResultVO= wareHouseRepository.findPageByCondition(warehouseQueryVO);
//        pageResultVO.getList().forEach(f->f.setStoreType(WarehouseTypeEnum.changeToForwardType(f.getStoreType())));
//        return pageResultVO;
//    }
//}
