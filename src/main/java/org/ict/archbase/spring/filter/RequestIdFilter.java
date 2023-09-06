package org.ict.archbase.spring.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.ict.archbase.spring.util.RequestUtil;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
public class RequestIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String reqId = httpServletRequest.getHeader(RequestUtil.REQ_ID_HEADER);
        if (StringUtils.isEmpty(reqId)) {
            reqId = UUID.randomUUID().toString();
        }

        RequestUtil.setRequestId(reqId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            RequestUtil.removeRequestId();
        }
    }
}
