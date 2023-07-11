package com.yr.springbootsecuritymybatiscrud.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

@Service
public class MyFilters extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    FilterInvocationSecurityMetadataSource securityMetadataSource;//该接口的实现类用于查出当前请求需要的访问权限

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //FilterInvocation封装了request将request\response安全的转为HttpServletRequest\HttpServletResponse
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(filterInvocation);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        // 鉴权主要就是这里在启作用
        InterceptorStatusToken token = super.beforeInvocation(fi);//去accessDecisionManager进行验证权限
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }  finally {
            super.finallyInvocation(token);
        }
        super.afterInvocation(token, null);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Autowired
    public void test(RoleFilter roleFilter){
        super.setAccessDecisionManager(roleFilter);//添加验证权限的过滤器类
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;//该接口的实现类用于查出当前请求需要的访问权限
    }
}
