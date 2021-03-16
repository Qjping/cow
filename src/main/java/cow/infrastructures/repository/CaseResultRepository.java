package cow.infrastructures.repository;

import com.mysql.cj.util.StringUtils;
import cow.infrastructures.jooq.tables.CaseResult;
import cow.infrastructures.jooq.tables.CaseResult.*;
import cow.infrastructures.jooq.tables.records.CaseResultRecord;
import cow.infrastructures.struct.vo1.CaseResultQueryVO;
import cow.infrastructures.struct.vo1.CaseResultVO;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static cow.infrastructures.jooq.tables.CaseResult.CASE_RESULT;

@Repository
public class CaseResultRepository {
    private final DSLContext create;
    private final CaseResult cs = CASE_RESULT;


    public CaseResultRepository(DSLContext create) {
        this.create = create;
    }

    public void  save(List<CaseResultVO> caseResultVOS){
        List<CaseResultRecord> recordList = new ArrayList<>();
        caseResultVOS.forEach(caseResultVO -> {
            recordList.add(create.newRecord(cs,caseResultVO));
        }
        );

        create.batchInsert(recordList).execute();
    }

    public CaseResultVO seach(CaseResultQueryVO caseResultQueryVO){
        List<Condition> condition = new ArrayList<>();
        if(!StringUtils.isNullOrEmpty(caseResultQueryVO.getGroupId().toString())) {
            condition.add(CASE_RESULT.ID.eq(caseResultQueryVO.getGroupId()));
        }
        return  create.selectFrom(CASE_RESULT)
                .where(condition).fetchOneInto(CaseResultVO.class);

    }

}
