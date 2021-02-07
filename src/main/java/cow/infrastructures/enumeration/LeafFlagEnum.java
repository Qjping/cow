package cow.infrastructures.enumeration;

import java.util.Arrays;

/**
 * @author QIUJINGPING
 *
 * @description
 */
public enum LeafFlagEnum {

    LEAF(1, "叶子节点"),
    NOT_LEAF(0, "非叶子节点"),
    UN_KNOW(-1, "")
    ;

    private Integer value;
    private String desc;

    LeafFlagEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static boolean isLeaf(Integer value) {
        return find(value) == LEAF;
    }

    public static LeafFlagEnum find(Integer value) {
        return Arrays.stream(LeafFlagEnum.values())
                .filter(item -> item.value.equals(value))
                .findFirst()
                .orElse(UN_KNOW);
    }

    public static boolean isRoot(Long id) {
        return id.equals(0l);
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
