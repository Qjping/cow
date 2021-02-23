package cow.infrastructures.repository;

import com.mysql.cj.util.StringUtils;
import cow.infrastructures.jooq.tables.UserAccount;
import cow.infrastructures.struct.vo1.UserAccountVO;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static cow.infrastructures.jooq.Tables.USER_DEFINE_PARAM;
import static cow.infrastructures.jooq.tables.UserAccount.USER_ACCOUNT;
@Repository
public class UserAccountRepository {
    private final DSLContext create;
    private final UserAccount ua = USER_ACCOUNT;

    public UserAccountRepository(DSLContext create) {
        this.create = create;
    }

    public UserAccountVO search(String account){
        List<Condition> condition = new ArrayList<>();
        if(!StringUtils.isNullOrEmpty(account)) {
            condition.add(USER_ACCOUNT.ACCOUNT.eq(account));
        }
        return  create.selectFrom(USER_ACCOUNT)
                .where(condition).fetchOneInto(UserAccountVO.class);

    }
}
