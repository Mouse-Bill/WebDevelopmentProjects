package org.bill.webhomework10;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "adminFilter", urlPatterns = {"/admin/*"})

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化操作，如果需要的话
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取当前用户的角色信息，这里假设你有一种方法可以获取角色
        String role = getRoleFromSession(request);

        if ("admin".equals(role)) {
            // 角色为"admin"，允许请求通过，继续处理
            filterChain.doFilter(request, response);
        } else {
            // 角色不是"admin"，拒绝访问，返回错误响应或重定向到其他页面
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }

    @Override
    public void destroy() {
        // 销毁操作，如果需要的话
    }

    // 从会话中获取用户角色的示例方法，你可能需要根据你的应用程序进行相应的更改
    private String getRoleFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("role");
        }
        return null;
    }
}
