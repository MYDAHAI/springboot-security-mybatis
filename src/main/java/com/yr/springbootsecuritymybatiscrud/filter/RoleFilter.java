package com.yr.springbootsecuritymybatiscrud.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

//实现验证权限的接口AccessDecisionManager
@Service
public class RoleFilter implements AccessDecisionManager {

    //configAttributes其实里面就是我们在配置文件中配置的过滤规则
    //Authentication：认证接口，定义了认证对象的数据形式 方法有：
    //  getAuthorities: 获取用户权限，一般情况下获取到的是用户的角色信息。
    //  getCredentials: 获取证明用户认证的信息，通常情况下获取到的是密码等信息。
    //  getDetails: 获取用户的额外信息，（这部分信息可以是我们的用户表中的信息）。
    //  getPrincipal: 获取用户身份信息，在未认证的情况下获取到的是用户名，在已认证的情况下获取到的是 UserDetails。
    //  isAuthenticated: 获取当前 Authentication 是否已认证。
    //  setAuthenticated: 设置当前 Authentication 是否已认证（true or false）。
    //中间的参数object就是我们调用beforeInvocation传入的FilterInvocation(封装了request的那个类)
    //UserDetail接口中，接口的内容包括用户名、密码、用户持有的权限、是否被锁定、是否过期等和权限相关的内容。
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null || configAttributes.size() <= 0) return;

        assert authentication != null;
        assert object != null;
        FilterInvocation fi = (FilterInvocation) object;
        // 拿到当前请求url
        String requestUrl = fi.getRequestUrl();
        String method = fi.getRequest().getMethod();

        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while(iterator.hasNext()){
            ConfigAttribute configAttribute = iterator.next();
            String attribute = configAttribute.getAttribute();//得到当前请求需要的权限
//            System.out.println("当前请求需要的权限--> " + attribute);

            if ("null".equals(attribute)) return;//请求需要的权限等于null，表示访问该url不需要权限，故直接放行

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//得到当前用户的权限集合
            for (GrantedAuthority authority : authorities){
                String auth = authority.getAuthority();//得到当前用户的权限
//                System.out.println("用户拥有的权限： " + auth);

                if(auth.equals("ROLE_" + attribute)){//权限匹配
//                    System.out.println("用户拥有权限");
                    return;
                }

            }
        }
        throw new AccessDeniedException("您当前没有访问权限！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
