package org.yxyqcy.family.sys.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by lcy on 17/6/13.
 *
 * 解决sitemesh  html修饰问题
 */
public class SiteMeshEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.setProperty("file.encoding","utf-8");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
