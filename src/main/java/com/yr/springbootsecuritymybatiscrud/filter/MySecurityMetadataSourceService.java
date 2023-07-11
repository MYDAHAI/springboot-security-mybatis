package com.yr.springbootsecuritymybatiscrud.filter;

import com.yr.springbootsecuritymybatiscrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by yangyibo on 17/1/19.
 */
@Service
public class MySecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private UserService userService;

    //此方法用来根据url到数据库中查出访问该url所需的权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();
        String method = filterInvocation.getRequest().getMethod();

        String role = userService.getPermissionByUrlMethod(url, method);//得到该请求需要的访问权限
        if("".equals(role) || role == null) role = "null";
        Collection<ConfigAttribute> co=new ArrayList<>();
        co.add(new SecurityConfig(role));
        return co;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
