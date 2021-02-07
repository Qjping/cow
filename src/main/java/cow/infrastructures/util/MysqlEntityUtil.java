package cow.infrastructures.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MysqlEntityUtil {
    private static final MysqlEntityUtil INSTANCE = new MysqlEntityUtil();

    private String tableName;// 表名
    private String[] colNames; // 列名数组
    private String[] colTypes; // 列名类型数组
    private int[] colSizes; // 列名大小数组
    private boolean needLocalDateTime = false; // 是否需要导入包java.time.LocalDateTime
    private boolean needLocalDate = false; // 是否需要导入包java.time.LocalDate
    private boolean needSql = false; // 是否需要导入包java.sql.*
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String SQL = "SELECT * FROM ";// 数据库操作

    // TODO 需要修改的地方				jdbc:mysql://192.9.8.144:3306/wym_guotu_xc
    private static final String URL = "jdbc:mysql://localhost:3306/cow?serverTimezone=GMT";
    private static final String TABLE_PREFIX = "user_";
    private static final String NAME = "root";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private String packageOutPath = "cow.infrastructures.struct.vo1";// 指定实体生成所在包的路径
    private String authorName = "qiujingping";// 作者名字
    private String[] generateTables = null;//指定需要生成的表的表名，全部生成设置为null

    /**
     * 类的构造方法
     */
    private MysqlEntityUtil() {
    }

    /**
     * @return
     * @description 生成class的所有内容
     * @author paul
     * @date 2017年8月18日 下午5:30:07
     * @update 2017年8月18日 下午5:30:07
     * @version V1.0
     */
    private String parse() {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + packageOutPath + ";\r\n");
        sb.append("\r\n");
        if (needLocalDateTime) {
            sb.append("import java.time.LocalDateTime;\r\n");
        }
        if (needLocalDate) {
            sb.append("import java.time.LocalDate;\r\n");
        }
        if (needSql) {
            sb.append("import java.sql.*;\r\n");
        }
        // 注释部分
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * table name: " + tableName + "\r\n");
        sb.append(" * author name: " + authorName + "\r\n");
        sb.append(" * create time: " + SDF.format(new Date()) + "\r\n");
        sb.append(" */ \r\n");
        // 实体部分
        sb.append("public class " + getFileName(tableName) + " {\r\n\r\n");
        processAllAttrs(sb);// 属性
        sb.append("\r\n");
        processAllMethod(sb);// get set方法
        processToString(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    private String getFileName(String tableName) {
        return getTransStr(tableName.substring(TABLE_PREFIX.length()), true) + "VO";
    }

    /**
     * @param sb
     * @description 生成所有成员变量
     * @author paul
     * @date 2017年8月18日 下午5:15:04
     * @update 2017年8月18日 下午5:15:04
     * @version V1.0
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("    private " + sqlType2JavaType(colTypes[i]) + " " + getTransStr(colNames[i], false) + ";\r\n");
        }
    }

    /**
     * 重写toString()方法
     * @param sb
     */
    private void processToString(StringBuffer sb) {
        sb.append("    @Override\r\n    public String toString() {\r\n");
        sb.append("        return \"" + getFileName(tableName)  + "{\" + \r\n");
        for (int i = 0; i < colNames.length; i++) {
            if (i != 0)
                sb.append("            \", ");
            if (i == 0)
                sb.append("            \"");
            String colName = getTransStr(colNames[i], false);


            sb.append(colName + "=");
            if ("String".equals(sqlType2JavaType(colTypes[i]))) {
                sb.append("'");
            }
            sb.append("\" + " + colName);
            if ("String".equals(sqlType2JavaType(colTypes[i]))) {
                sb.append(" + \'\\'\'");
            }
            sb.append(" + \r\n");
            if (i == colNames.length - 1) {
                sb.append("            \"}\";\r\n");
            }
        }
        sb.append("    }\r\n");
    }

    /**
     * @param sb
     * @description 生成所有get/set方法
     * @author paul
     * @date 2017年8月18日 下午5:14:47
     * @update 2017年8月18日 下午5:14:47
     * @version V1.0
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colNames.length; i++) {
            sb.append("    public void set" + getTransStr(colNames[i], true) + "(" + sqlType2JavaType(colTypes[i]) + " "
                    + getTransStr(colNames[i], false) + "){\r\n");
            sb.append("        this." + getTransStr(colNames[i], false) + "=" + getTransStr(colNames[i], false) + ";\r\n");
            sb.append("    }\r\n");
            sb.append("\r\n");
            sb.append("    public " + sqlType2JavaType(colTypes[i]) + " get" + getTransStr(colNames[i], true) + "(){\r\n");
            sb.append("        return " + getTransStr(colNames[i], false) + ";\r\n");
            sb.append("    }\r\n");
            sb.append("\r\n");
        }
    }

    /**
     * @param str 传入字符串
     * @return
     * @description 将传入字符串的首字母转成大写
     * @author paul
     * @date 2017年8月18日 下午5:12:12
     * @update 2017年8月18日 下午5:12:12
     * @version V1.0
     */
    private String initCap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z')
            ch[0] = (char) (ch[0] - 32);
        return new String(ch);
    }

    /**
     * @return
     * @description 将mysql中表名和字段名转换成驼峰形式
     * @author paul
     * @date 2017年8月18日 下午4:55:07
     * @update 2017年8月18日 下午4:55:07
     * @version V1.0
     */
    private String getTransStr(String before, boolean firstChar2Upper) {
        //不带"_"的字符串,则直接首字母大写后返回
        if (!before.contains("_"))
            return firstChar2Upper ? initCap(before) : before;
        String[] strs = before.split("_");
        StringBuffer after = null;
        if (firstChar2Upper) {
            after = new StringBuffer(initCap(strs[0]));
        } else {
            after = new StringBuffer(strs[0]);
        }
        if (strs.length > 1) {
            for (int i = 1; i < strs.length; i++)
                after.append(initCap(strs[i]));
        }
        return after.toString();
    }

    /**
     * @return
     * @description 查找sql字段类型所对应的Java类型
     * @author paul
     * @date 2017年8月18日 下午4:55:41
     * @update 2017年8月18日 下午4:55:41
     * @version V1.0
     */
    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit") || sqlType.equalsIgnoreCase("tinyint")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "LocalDateTime";
        } else if (sqlType.equalsIgnoreCase("date")) {
            return "LocalDate";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }
        return null;
    }

    /**
     * @throws Exception
     * @description 生成方法
     * @author paul
     * @date 2017年8月18日 下午2:04:20
     * @update 2017年8月18日 下午2:04:20
     * @version V1.0
     */
    private void generate() throws Exception {
        //与数据库的连接
        Connection con;
        PreparedStatement pStemt = null;
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, NAME, PASS);
        System.out.println("connect database success...");
        //获取数据库的元数据
        DatabaseMetaData db = con.getMetaData();
        //是否有指定生成表，有指定则直接用指定表，没有则全表生成
        List<String> tableNames = new ArrayList<>();
        if (generateTables == null) {
            //从元数据中获取到所有的表名
            ResultSet rs = db.getTables(null, null, TABLE_PREFIX + "%", new String[]{"TABLE"});
            while (rs.next()) tableNames.add(rs.getString(3));
        } else {
            for (String tableName : generateTables) tableNames.add(tableName);
        }
        String tableSql;
        PrintWriter pw = null;
        for (int j = 0; j < tableNames.size(); j++) {
            tableName = tableNames.get(j);
            tableSql = SQL + tableName;
            pStemt = con.prepareStatement(tableSql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();
            colNames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            //获取所需的信息
            for (int i = 0; i < size; i++) {
                colNames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                if (colTypes[i].equalsIgnoreCase("datetime"))
                    needLocalDateTime = true;
                if (colTypes[i].equalsIgnoreCase("date"))
                    needLocalDate = true;
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text"))
                    needSql = true;
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            //解析生成class的所有内容
            String content = parse();
            //输出生成文件
            File directory = new File("");
            String dirName = directory.getAbsolutePath() + "/src/main/java/" + packageOutPath.replace(".", "/");
            File dir = new File(dirName);
            if (!dir.exists() && dir.mkdirs()) System.out.println("generate dir 【" + dirName + "】");
            String javaPath = dirName + "/" + getFileName(tableName)  + ".java";
            FileWriter fw = new FileWriter(javaPath);
            pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            System.out.println("create class 【" + getFileName(tableName)  + "】");
        }
        if (pw != null)
            pw.close();
    }

    /**
     * @param args
     * @description 执行方法
     * @author paul
     * @date 2017年8月18日 下午2:03:35
     * @update 2017年8月18日 下午2:03:35
     * @version V1.0
     */
    public static void main(String[] args) {
        try {
            INSTANCE.generate();
            System.out.println("generate classes success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
