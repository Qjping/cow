package cow.infrastructures.converter;

import cow.infrastructures.struct.ido.LoginQueryIDO;
import cow.infrastructures.struct.ido.LoginUserIDO;
import cow.infrastructures.struct.vo1.LoginQueryVO;
import cow.infrastructures.struct.vo1.UserAccountVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserConverter {
    LoginQueryVO idoToVO(LoginQueryIDO queryIDO);
    LoginUserIDO voToIdo(UserAccountVO userAccountVO);
}
