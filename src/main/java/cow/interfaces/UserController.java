package cow.interfaces;

import cow.applications.service.UserService;
import cow.infrastructures.constant.RedisConstant;
import cow.infrastructures.repository.UserAccountRepository;
import cow.infrastructures.struct.ido.ApiResultIDO;


import cow.infrastructures.struct.ido.LoginQueryIDO;
import cow.infrastructures.struct.ido.LoginUserIDO;
import cow.infrastructures.struct.vo1.UserAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final HttpServletRequest request;
    private StringRedisTemplate redisTemplate;

    public UserController(UserAccountRepository userAccountRepository, UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @PostMapping("login")
    public ApiResultIDO<LoginUserIDO> login(@RequestBody LoginQueryIDO loginQueryIDO){
        log.info(String.valueOf(loginQueryIDO));
        LoginUserIDO loginUserIDO = userService.login(loginQueryIDO);
        return ApiResultIDO.success(loginUserIDO);
    }
}
