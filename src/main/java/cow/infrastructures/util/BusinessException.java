

package cow.infrastructures.util;

public class BusinessException extends RuntimeException {
    private final Integer code;
    private final String message;

    public BusinessException(Integer code, String message, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
