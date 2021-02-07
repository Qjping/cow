package cow.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import cow.infrastructures.enumeration.ExceptionCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author napuping
 * @date 2020-11-26 上午9:42
 * @description
 */
//@Component
public class HeaderInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(HeaderInterceptor.class);

    private final ObjectMapper objectMapper;

    public HeaderInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod)) {
//           return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        if (checkHeaderWarehouseId(request, handlerMethod)) {
//            respJson(response, ExceptionCodeEnum.NO_HEADER_WAREHOUSE_ID);
//            return false;
//        }
//        return true;
//    }
//
//    private boolean checkHeaderWarehouseId(HttpServletRequest request, HandlerMethod handlerMethod) {
//        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//        if (methodParameters.length == 0) {
//            return false;
//        }
//        boolean existAnnotation = Arrays.stream(methodParameters).anyMatch(item -> {
//            RequestHeader annotation = item.getParameterAnnotation(RequestHeader.class);
//            return annotation != null && annotation.required() && CommonConstant.warehouseIdH.equals(annotation.value());
//        });
//        if (!existAnnotation) {
//            return false;
//        }
//        return StringUtils.isEmpty(request.getHeader(CommonConstant.warehouseIdH));
//    }

    private void respJson(HttpServletResponse response, ExceptionCodeEnum codeEnum) {
        try {
            response.setContentType("text/html; charset=utf-8");
            Map<String, Object> param = new HashMap<>();
            param.put("code", codeEnum.getCode());
            param.put("message", codeEnum.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(param));
        } catch (Exception e) {
            logger.warn("respJson fail:{}", e.getMessage());
        }
    }

}
