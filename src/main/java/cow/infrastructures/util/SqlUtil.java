package cow.infrastructures.util;

/**
 * @author napuping
 * @date 2020-11-23 下午2:51
 * @description
 */
public class SqlUtil {

    public final static String likeChar = "%";

    public static String like(String str) {
       return like(likeChar, str, likeChar);
    }

    private static String like(String left, String str, String right) {
        return left + str + right;
    }

    public static String leftLike(String str) {
        return like(likeChar, str, "");
    }

    public static String rightLike(String str) {
        return like("", str, likeChar);
    }
}
