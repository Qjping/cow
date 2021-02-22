package cow.infrastructures.converter;

import cow.infrastructures.jooq.tables.UserAccount;
import cow.infrastructures.struct.ido.LoginQueryIDO;
import cow.infrastructures.struct.ido.LoginUserIDO;
import cow.infrastructures.struct.vo1.LoginQueryVO;
import cow.infrastructures.struct.vo1.UserAccountVO;

public interface UserConverter {
    LoginQueryVO idoToVO(LoginQueryIDO queryIDO);
    LoginUserIDO voToIdo(UserAccountVO userAccountVO);
}
