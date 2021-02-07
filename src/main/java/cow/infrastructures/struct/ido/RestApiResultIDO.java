//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cow.infrastructures.struct.ido;

public class RestApiResultIDO<T> {
    private Integer code = 20000;
    private String message = "ok";
    private T data = null;

    public RestApiResultIDO() {
    }

    public RestApiResultIDO(T data) {
        this.data = data;
    }

    public RestApiResultIDO(Integer code, String errorMessage) {
        this.code = code;
        this.message = errorMessage;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }
}
