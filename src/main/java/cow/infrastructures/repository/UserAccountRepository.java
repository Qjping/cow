package cow.infrastructures.repository;

import cow.infrastructures.jooq.tables.UserAccount;
import cow.infrastructures.jooq.tables.UserDefineParam;
import cow.infrastructures.struct.vo1.UserAccountVO;
import cow.infrastructures.struct.vo1.UserDefineParamVO;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cow.infrastructures.jooq.Tables.CASE_DETAIL;
import static cow.infrastructures.jooq.Tables.USER_DEFINE_PARAM;
import static cow.infrastructures.jooq.tables.UserAccount.USER_ACCOUNT;
@Repository
public class UserAccountRepository {
    private final DSLContext create;
    private final UserAccount ua = USER_ACCOUNT;

    public UserAccountRepository(DSLContext create) {
        this.create = create;
    }

    public UserAccountVO seach(String name){
        List<Condition> condition = new ArrayList<>();
        condition.add(USER_ACCOUNT.ACCOUNT.eq(name));


        return  create.selectFrom(USER_DEFINE_PARAM)
                .where(condition).fetchOneInto(UserAccountVO.class);


    }
}
