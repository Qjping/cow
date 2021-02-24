package cow.infrastructures.repository;

import cow.infrastructures.jooq.tables.CaseGroup;
import cow.infrastructures.struct.ido.CaseGroupIDO;
import cow.infrastructures.struct.ido.CaseGroupQueryIDO;
import cow.infrastructures.struct.ido.PageResultIDO;
import cow.infrastructures.struct.vo1.*;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cow.infrastructures.jooq.Tables.CASE_DETAIL;
import static cow.infrastructures.jooq.tables.CaseGroup.CASE_GROUP;

@Repository
public class CaseGroupRepository {
    private final DSLContext create;
    private final CaseGroup cg = CASE_GROUP;

    public CaseGroupRepository(DSLContext create) {
        this.create = create;
    }

    public PageResultVO<CaseGroupVO> search(CaseGroupQueryVO caseGroupQueryVO){
        List<Condition> condition= new ArrayList<>();
        if(caseGroupQueryVO.getDescription()!=null){
            condition.add(CASE_GROUP.DESCRIPTION.like(caseGroupQueryVO.getDescription()));
        }
        return new PageResultVO<>(create.selectFrom(CASE_GROUP).
                where(condition).
                offset(Math.max((caseGroupQueryVO.getPage() - 1) * caseGroupQueryVO.getLimit(), 0)).
                 fetchStream()
                .map(v -> v.into(CaseGroupVO.class))
                .collect(Collectors.toList()),
        create.selectCount().from(CASE_DETAIL).where(condition).fetchOne().value1()
        );
    }
}
