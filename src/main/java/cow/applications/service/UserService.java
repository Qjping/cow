package cow.applications.service;

import cow.infrastructures.constant.RedisConstant;
import cow.infrastructures.converter.UserConverter;
import cow.infrastructures.enumeration.ExceptionCodeEnum;
import cow.infrastructures.repository.UserAccountRepository;
import cow.infrastructures.struct.ido.LoginQueryIDO;
import cow.infrastructures.struct.ido.LoginUserIDO;
import cow.infrastructures.struct.vo1.LoginQueryVO;
import cow.infrastructures.struct.vo1.UserAccountVO;
import cow.infrastructures.util.BusinessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private final UserAccountRepository userAccountRepository;
    private final UserConverter userConverter;
    private final StringRedisTemplate redisTemplate;



    public UserService(UserAccountRepository userAccountRepository, UserConverter userConverter, StringRedisTemplate redisTemplate) {
        this.userAccountRepository = userAccountRepository;
        this.userConverter = userConverter;
        this.redisTemplate = redisTemplate;
    }

    public LoginUserIDO login(LoginQueryIDO loginQueryIDO){
        LoginQueryVO loginQueryVO = userConverter.idoToVO(loginQueryIDO);


        if(loginQueryVO.getAccount()==null){
            throw new BusinessException(ExceptionCodeEnum.UNEXPECTED_EXCEPTION.getCode(),"用户不存在");
        }
        UserAccountVO userAccountVO = userAccountRepository.seach(loginQueryVO.getAccount());
        if(userAccountVO==null){
            throw new BusinessException(ExceptionCodeEnum.UNEXPECTED_EXCEPTION.getCode(),"密码不正确");
        }
        if(!userAccountVO.getAccount().equals(loginQueryIDO.getAccount())){
            throw new BusinessException(ExceptionCodeEnum.UNEXPECTED_EXCEPTION.getCode(),"密码不正确");
        }
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),userAccountVO.getAccount(),RedisConstant.EXPIRE, TimeUnit.SECONDS);
        LoginUserIDO loginUserIDO = userConverter.voToIdo(userAccountVO);
        loginUserIDO.setToken(token);
        return loginUserIDO;
    }
}
