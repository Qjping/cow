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
//        "/merp-api/wms/outbound-apply/transfer-search",
//        "/merp-api/wms/stocktaking/search",
//        "/merp-api/wms/transfer/get-transfer-list",
//        "/merp-api/wms/inbound-apply/search",
//        "/merp-api/wms/goods-brand/search",
//        "/merp-api/wms/goods-tag/search",
//        "/merp-api/wms/goods-unit/search",
//        "/merp-api/wms/sku/get-sku-list",
//        "/merp-api/wms/goods-category/search",
//        "/merp-api/wms/goods-category/find-tree-data",
//        "/merp-api/wms/i-sku/get-sku-list",
//        "/merp-api/wms/inbound-apply/get-sku-info",
//        "/merp-api/wms/warehouse-goods-sku/search",
//        "/merp-api/wms/cell-inventory/get-sku-inventory-detail",
//        "/merp-api/wms/transfer/get-out-detail-by-basic",
//        "/merp-api/wms/transfer/get-in-detail-by-basic",
//        "/merp-api/wms/transfer/get-out-detail-by-business",
//        "/merp-api/wms/transfer/get-in-detail-by-business",
//        "/merp-api/wms/area/get-area-group-list",
//        "/merp-api/wms/cell/get-pick-cell-number",
//        "/merp-api/wms/cell-inventory/get-sku-List-no-inventory"
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
