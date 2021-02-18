package cow.infrastructures.repository;

import cow.infrastructures.jooq.tables.UserDefineParam;
import cow.infrastructures.struct.vo1.CaseQueryVO;
import cow.infrastructures.struct.vo1.UserDefineParamVO;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static cow.infrastructures.jooq.Tables.USER_DEFINE_PARAM;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDefineParamRepository {
    private final DSLContext create;
    private final UserDefineParam udp = UserDefineParam.USER_DEFINE_PARAM;

    public UserDefineParamRepository(DSLContext create) {
        this.create = create;
    }

    public List<UserDefineParamVO> seach(CaseQueryVO caseQueryVO) {
        List<Condition> condition = new ArrayList<>();

        if (caseQueryVO.getGroupId() != null) {
            condition.add(USER_DEFINE_PARAM.CASE_GROUP_ID.eq(caseQueryVO.getGroupId()));
        }

        return create.selectFrom(USER_DEFINE_PARAM)
                .where(condition)
                .fetchStream()
                .map(v -> v.into(UserDefineParamVO.class))
                .collect(Collectors.toList());


    }
}
