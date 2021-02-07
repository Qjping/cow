package cow.infrastructures.struct.ido;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ApiResultIDO<T> implements Serializable {

    public static Integer successCode = 20000;
    public static Integer errorCode = 50000;
    private static String successMessage = "ok";
    private static String failMessage = "fail";

    private T data;
    private Integer code;
    private String message;

    public ApiResultIDO() {
    }

    public ApiResultIDO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResultIDO<T> result(Integer code, String message) {
        return result(code, message, null);
    }

    public static <T> ApiResultIDO<T> result(Integer code, String message, T data) {
        return new ApiResultIDO<>(code, message, data);
    }

    public static <T> ApiResultIDO<T> success(T data) {
        return success(data, null);
    }

    public static <T> ApiResultIDO<T> success() {
        return success(null, null);
    }

    public static <T> ApiResultIDO<T> success(String message) {
        return success(null, message);
    }

    public static <T> ApiResultIDO<T> success(T data, String message) {
        message = message == null || "".equals(message) ? ApiResultIDO.successMessage : message;
        return new ApiResultIDO<>(successCode, message, data);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return code.equals(successCode);
    }

    @JsonIgnore
    public boolean isFail() {
        return !code.equals(successCode);
    }

    public static <T> ApiResultIDO<T> fail(String message) {
        return fail(errorCode, message);
    }

    public static <T> ApiResultIDO<T> fail(Integer code, String message, T data) {
        return new ApiResultIDO<>(code, message, data);
    }

    public static <T> ApiResultIDO<T> fail(Integer code, String message) {
        return fail(code, message, null);
    }

    public static <T> ApiResultIDO<T> fail(T data) {
        return fail(errorCode, failMessage, data);
    }

    public static <T> ApiResultIDO<T> fail() {
        return fail(errorCode, failMessage, null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RestApiResultIDO{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
