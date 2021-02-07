package cow.infrastructures.enumeration;

public enum ExceptionCodeEnum {
    ARGUMENT_NOT_VALID_EXCEPTION(40000),
    INVENTORY_NOT_ENOUGH(40001),
    UNEXPECTED_EXCEPTION(50000),
    NO_ACCESS_TOKEN(44101),
    NO_MARKETING_STATISTIC(69001),
    BOOKED_CAN_NOT_UPDATE_PICKUP_SITE(69002),
    BOOKED_CAN_NOT_UPDATE_ISOLATED(69003),
    NO_LOGIN_EXCEPTION(44000, "未登录或token已过期,请重新登录"),
    ILL_ACCESS_TOKEN(44101, "用户token不合法"),
    NO_HEADER_WAREHOUSE_ID(44102, "请选择仓库"),
    DUPLICATE_KEY_EXCEPTION(49049, "唯一值冲突");


    private final Integer code;
    private String message;

    ExceptionCodeEnum(Integer code) {
        this.code = code;
    }

    ExceptionCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
