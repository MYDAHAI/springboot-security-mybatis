package com.yr.springbootsecuritymybatiscrud.config;

import com.yr.springbootsecuritymybatiscrud.filter.MyAccessDeniedHandler;
import com.yr.springbootsecuritymybatiscrud.filter.MyFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//会拦截注解了@PreAuthrize、@prePostEnabled注解的配置
//当@EnableGlobalMethodSecurity(securedEnabled=true)的时候，@Secured可以使用：
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;//去数据库认证密码

//	@Autowired
//	DataSource dataSource;

    @Autowired
    MyFilters myFilters;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;


    //用作加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义认证，去数据库查用户信息封装进行认证   		认证时会加密密码
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/loginPage").permitAll()
                //			.anyRequest().access("@myAccessServiceImpl.myUri(request,authentication)")
                // 除了上面，所有请求都要认证
                .anyRequest().authenticated()

                .and()
                    .formLogin()//打开form表单认证方式，即UsernamePasswordAuthenticationFilter过滤器
//    				.loginPage("/loginPage")
    //				.successHandler(customSuccessHandler)
                    .defaultSuccessUrl("/getUserAll")
                    .failureUrl("/loginPage")
                    .usernameParameter("username")
                    .passwordParameter("password")

//			.and()
//				.rememberMe()//记住我
//				.rememberMeParameter("remember-me")
//				.tokenRepository(persistentTokenRepository())
//				.tokenValiditySeconds(86400)//记住多长时间
//				.userDetailsService(userDetailsService)//UserDetailsService 是必需的。

                .and()
                    .logout()
    //				.logoutSuccessUrl("/loginPage")
//                    .logoutUrl("logout")
                    //添加 /logout 能够以 GET 请求的配置
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))

                .and()
                    .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)//自定义没权限页面
                .and()
                    .cors()//跨域
                    .configurationSource(corsConfigurationSource());

        http.addFilterBefore(myFilters, FilterSecurityInterceptor.class).csrf().disable();

//			csrf().disable();//关闭csrf
    }

    //跨域设置
    CorsConfigurationSource corsConfigurationSource() {
        // 提供CorsConfiguration实例，并配置跨域信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
//		tokenRepositoryImpl.setDataSource(dataSource);
//		return tokenRepositoryImpl;
//	}
}