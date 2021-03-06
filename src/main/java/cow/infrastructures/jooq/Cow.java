/*
 * This file is generated by jOOQ.
 */
package cow.infrastructures.jooq;


import cow.infrastructures.jooq.tables.CaseConfig;
import cow.infrastructures.jooq.tables.CaseDetail;
import cow.infrastructures.jooq.tables.CaseGroup;
import cow.infrastructures.jooq.tables.CaseResult;
import cow.infrastructures.jooq.tables.FlywaySchemaHistory;
import cow.infrastructures.jooq.tables.TestConfig;
import cow.infrastructures.jooq.tables.UserAccount;
import cow.infrastructures.jooq.tables.UserDefineParam;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Cow extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>cow</code>
     */
    public static final Cow COW = new Cow();

    /**
     * The table <code>cow.case_config</code>.
     */
    public final CaseConfig CASE_CONFIG = CaseConfig.CASE_CONFIG;

    /**
     * The table <code>cow.case_detail</code>.
     */
    public final CaseDetail CASE_DETAIL = CaseDetail.CASE_DETAIL;

    /**
     * The table <code>cow.case_group</code>.
     */
    public final CaseGroup CASE_GROUP = CaseGroup.CASE_GROUP;

    /**
     * The table <code>cow.case_result</code>.
     */
    public final CaseResult CASE_RESULT = CaseResult.CASE_RESULT;

    /**
     * The table <code>cow.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>cow.test_config</code>.
     */
    public final TestConfig TEST_CONFIG = TestConfig.TEST_CONFIG;

    /**
     * The table <code>cow.user_account</code>.
     */
    public final UserAccount USER_ACCOUNT = UserAccount.USER_ACCOUNT;

    /**
     * The table <code>cow.user_define_param</code>.
     */
    public final UserDefineParam USER_DEFINE_PARAM = UserDefineParam.USER_DEFINE_PARAM;

    /**
     * No further instances allowed
     */
    private Cow() {
        super("cow", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            CaseConfig.CASE_CONFIG,
            CaseDetail.CASE_DETAIL,
            CaseGroup.CASE_GROUP,
            CaseResult.CASE_RESULT,
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            TestConfig.TEST_CONFIG,
            UserAccount.USER_ACCOUNT,
            UserDefineParam.USER_DEFINE_PARAM);
    }
}
