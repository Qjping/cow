package cow.interfaces;


import cow.infrastructures.annotation.LoginUser;
import cow.infrastructures.struct.ido.ApiResultIDO;
import cow.infrastructures.struct.ido.LoginUserIDO;
import cow.infrastructures.struct.ido.fixed.UserToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/get-user-info")
    public ApiResultIDO<LoginUserIDO> getUserInfo(@LoginUser @ApiIgnore UserToken userToken,
                                                  @RequestParam(value = "user_id", required = false) String userId){
   return null;
    }
}
