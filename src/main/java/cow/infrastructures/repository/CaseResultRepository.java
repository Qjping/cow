package cow.infrastructures.repository;

import cow.infrastructures.jooq.tables.CaseResult;
import cow.infrastructures.jooq.tables.records.CaseResultRecord;
import cow.infrastructures.struct.vo1.CaseResultVO;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CaseResultRepository {
    private final DSLContext create;
    private final CaseResult cs = CaseResult.CASE_RESULT;


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
}
