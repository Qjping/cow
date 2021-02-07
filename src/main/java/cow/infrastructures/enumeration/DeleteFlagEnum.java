package cow.infrastructures.enumeration;

import java.util.Arrays;


/**
 * @author QIUJINGPING
 *
 * @description
 */
public enum DeleteFlagEnum {

    USING(1, "未删除"),
    DELETED(0, "已删除"),
    UN_KNOW(-1, "")
    ;

    private Integer value;
    private String desc;

    DeleteFlagEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static DeleteFlagEnum find(Integer value) {
        return Arrays.stream(DeleteFlagEnum.values())
                .filter(item -> item.value.equals(value))
                .findFirst()
                .orElse(UN_KNOW);
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
