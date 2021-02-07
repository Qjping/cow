package cow.infrastructures.repository;

import cow.infrastructures.jooq.tables.CaseDetail;
import cow.infrastructures.jooq.tables.records.CaseDetailRecord;
import cow.infrastructures.repository.CaseAddVO;
import cow.infrastructures.struct.ido.CaseQueryIDO;

import cow.infrastructures.struct.vo1.CaseDetailAddVO;
import cow.infrastructures.struct.vo1.CaseDetailVO;

import cow.infrastructures.struct.vo1.CaseQueryVO;
import cow.infrastructures.struct.vo1.PageResultVO;
import org.jooq.Case;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.tools.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cow.infrastructures.jooq.Tables.CASE_DETAIL;

@Repository
public class CaseDetailRepository {
    private final DSLContext create;


    public CaseDetailRepository(DSLContext create) {
        this.create = create;
    }

    public PageResultVO<CaseDetailVO> getCaseDetailListByCondition(CaseQueryVO caseQueryVo) {
        List<Condition> condition = new ArrayList<>();

        if (caseQueryVo.getId() != null) {
            condition.add(CASE_DETAIL.ID.eq(caseQueryVo.getId()));
        }
        if (!StringUtils.isEmpty(caseQueryVo.getGroupId())) {
            condition.add(CASE_DETAIL.GROUP_ID.like('%' + caseQueryVo.getGroupId() + '%'));
        }

        return new PageResultVO<>(create.selectFrom(CASE_DETAIL)
                .where(condition)
                .orderBy(CASE_DETAIL.CREATED_AT.desc())
                .limit(caseQueryVo.getLimit())
                .offset(Math.max((caseQueryVo.getPage() - 1) * caseQueryVo.getLimit(), 0))
                .fetchStream()
                .map(v -> v.into(CaseDetailVO.class))
                .collect(Collectors.toList()),
                create.selectCount().from(CASE_DETAIL).where(condition).fetchOne().value1());
    }


    public void addCase(CaseDetailAddVO caseAddVO) {
        CaseDetailRecord newRecord = create.newRecord(CASE_DETAIL, caseAddVO);
        create.executeInsert(newRecord);

    }

}

