//package cow.applications.cache;
//
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Repository;
//import wewin.applications.remote.wrapper.UserService;
//import wewin.infrastructures.struct.vo.UserVO;
//
///**
// * @author napuping
// * @date 2021-01-04 下午6:45
// * @description
// */
//@Repository
//@CacheConfig(cacheNames = "wms::userCache")
//public class UserCacheService {
//
//    private final UserService userService;
//
//    public UserCacheService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Cacheable(key = "'findById:' + #p0", unless = "#result == null", cacheManager = "tenMinCacheManager")
//    public UserVO findById(String userId) {
//        return userService.findById(userId);
//    }
//
//}
