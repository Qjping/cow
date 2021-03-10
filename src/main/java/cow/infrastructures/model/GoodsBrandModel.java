package cow.infrastructures.model;

//import net.sourceforge.pinyin4j.PinyinHelper;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import wewin.common.infrastructures.struct.ido.fixed.UserToken;
//import wewin.infrastructures.enumeration.SurviveStatusEnum;
//import wewin.infrastructures.exception.BusinessException;
//import wewin.infrastructures.repository.GoodsBrandRepository;
//import wewin.infrastructures.struct.vo.GoodsBrandOperationVO;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Scope("prototype")
//@Component
//public class GoodsBrandModel {
//
//    private final GoodsBrandRepository goodsBrandRepository;
//
//    private UserToken userToken;
//    private GoodsBrandOperationVO goodsBrandOperationVO;
//
//    public GoodsBrandModel(GoodsBrandRepository goodsBrandRepository) {
//        this.goodsBrandRepository = goodsBrandRepository;
//    }
//
//
//    public GoodsBrandModel setUserToken(UserToken userToken) {
//        this.userToken = userToken;
//        return this;
//    }
//
//    public GoodsBrandModel setGoodsBrandOperationVO(GoodsBrandOperationVO goodsBrandOperationVO) {
//        this.goodsBrandOperationVO = goodsBrandOperationVO;
//        return this;
//    }
//
//    public void handleSave() {
//        check();
//        handleFirstLetter();
//        Long id = goodsBrandOperationVO.getId();
//        if (StringUtils.isEmpty(id)) {
//            handleAdd();
//            return;
//        }
//        handleUpdate();
//    }
//
//    private void check() {
//        int i = goodsBrandRepository.selectCountName(goodsBrandOperationVO.getName(), goodsBrandOperationVO.getId());
//        if (i > 0) {
//            throw new BusinessException("品牌名重复");
//        }
//    }
//
//    private void handleFirstLetter() {
//        try {
//            String name = goodsBrandOperationVO.getName().substring(0, 1);
//            String firstLetter;
//            if (name.matches("^[a-z0-9A-Z]+$")) {
//                firstLetter = name.toUpperCase();
//            } else {
//                String[] cs = PinyinHelper.toHanyuPinyinStringArray(name.charAt(0));
//                if (cs.length == 0) {
//                    return;
//                }
//                firstLetter = cs[0].substring(0, 1).toUpperCase();
//            }
//            goodsBrandOperationVO.setFirstLetter(firstLetter);
//        } catch (Exception e) {
//        }
//    }
//
//    private void handleAdd() {
//        goodsBrandOperationVO.setStatus(SurviveStatusEnum.USING.getValue());
//        goodsBrandOperationVO.setCreatedBy(userToken.getUserId());
//        goodsBrandOperationVO.setRecordDate(LocalDate.now());
//        goodsBrandOperationVO.setCreatedAt(LocalDateTime.now());
//        goodsBrandRepository.insert(goodsBrandOperationVO);
//    }
//
//    private void handleUpdate() {
//        goodsBrandOperationVO.setUpdatedBy(userToken.getUserId());
//        goodsBrandRepository.update(goodsBrandOperationVO);
//    }
//
//    public void updateStatus() {
//        goodsBrandRepository.updateStatus(userToken, goodsBrandOperationVO.getId(), goodsBrandOperationVO.getStatus());
//    }
//
//    public void delete(Long id) {
//        goodsBrandRepository.deleteById(id);
//    }
//
//}
