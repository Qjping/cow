//package cow.config;
//
//import wewin.webkit.infrastructures.config.SnakeCaseQueryStringRequestWrapper;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {
//        "/merp-api/wms/outbound-apply/search",

//}, filterName = "snakeCaseQueryStringFilter")
//public class SnakeCaseQueryStringConvertFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(new SnakeCaseQueryStringRequestWrapper((HttpServletRequest) servletRequest),
//                servletResponse);
//    }
//}
