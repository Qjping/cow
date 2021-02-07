package cow.config;

import cow.infrastructures.enumeration.ExceptionCodeEnum;
import cow.infrastructures.struct.ido.RestApiResultIDO;
import cow.infrastructures.util.BusinessException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
public class MsGlobalRestApiExceptionResolver {
    private final Logger logger = LoggerFactory.getLogger(MsGlobalRestApiExceptionResolver.class);
    private final HttpServletResponse httpServletResponse;

    public MsGlobalRestApiExceptionResolver(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @ExceptionHandler(BusinessException.class)
    public RestApiResultIDO<Void> handleException(BusinessException e) {
        logger.debug("参数错误：{}", ExceptionUtils.getStackTrace(e));
        return new RestApiResultIDO<>(e.getCode(), e.getMessage());
    }

    /**
     * 处理不可预料的系统异常
     */
    @ExceptionHandler(Exception.class)
    public RestApiResultIDO<Void> handleException(Exception e) {
        logger.error("系统错误：{}", ExceptionUtils.getStackTrace(e));
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new RestApiResultIDO<>(ExceptionCodeEnum.UNEXPECTED_EXCEPTION.getCode(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestApiResultIDO<Void> handleException(MethodArgumentNotValidException exception) {
        logger.error("Unexpected errors：\n{}", ExceptionUtils.getStackTrace(exception));
        BindingResult result = exception.getBindingResult();
        StringBuilder errorMsg = new StringBuilder() ;
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            errorMsg.append(fieldErrors.get(0).getDefaultMessage()).append("!");
        }
        return new RestApiResultIDO<>(ExceptionCodeEnum.UNEXPECTED_EXCEPTION.getCode(), errorMsg.toString());
    }

    @ExceptionHandler({BindException.class})
    public RestApiResultIDO<Void> handleException(BindException e) {
        logger.error("参数错误", e);
        return new RestApiResultIDO<>(ExceptionCodeEnum.UNEXPECTED_EXCEPTION.getCode(), (e.getBindingResult().getFieldErrors().get(0)).getDefaultMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public RestApiResultIDO<Void> handleException(DuplicateKeyException e) {
        logger.error("数据重复：{}", ExceptionUtils.getStackTrace(e));
        return new RestApiResultIDO<>(ExceptionCodeEnum.DUPLICATE_KEY_EXCEPTION.getCode(), "唯一值冲突， 请确保唯一值没有重复");
    }
}
