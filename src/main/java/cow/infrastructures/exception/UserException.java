//package cow.infrastructures.exception;
//
//
//import com.enmu.ResultEnum;
//import lombok.Getter;
//
//@Getter
//public class UserException extends  RuntimeException {
//    private Integer code;
//
//    public UserException(ResultEnum resultEnum){
//        super(resultEnum.getMessage());
//        this.code=resultEnum.getCode();
//    }
//    public UserException(Integer code, String msg){
//        super(msg);
//        this.code=code;
//
//    }
//
//}
