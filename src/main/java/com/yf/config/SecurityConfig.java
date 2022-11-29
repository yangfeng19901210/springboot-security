package com.yf.config;

import com.yf.handler.MyAuthenticationSuccessHandler;
import com.yf.service.MyUserDetailsService;
import com.yf.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @projectName: springboot-security
 * @package: com.yf.config
 * @className: SecurityConfig
 * @author: yangfeng
 * @description: spring-security配置类
 * @date: 2022/11/23 15:26
 * @version: 1.0
 */
//将该配置类交给spring容器管理
@Component
//开启过滤器链对请求的拦截
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAuthenticationSuccessHandler successHandler;
    @Resource
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private MyUserDetailsService userDetailsService;
    /**
     * @Author yangfeng
     * @Description //配置认证用户信息
     *              //配置认证用户的权限
     * @Date 15:46 2022/11/23
     * @param auth:
     * @return void
     * authentication 认证：检查并确认身份，确定身份是否能通过系统的第一道大门
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("123456")
//                .authorities("addOrder","showOrder","updateOrder","deleteOrder");
//        auth.inMemoryAuthentication().withUser("userAdd").password("123456").authorities("showOrder","addOrder");
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            //对表单的密码加密处理
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }
            //表单密码和数据库密码做比对
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return this.encode(rawPassword).equals(encodedPassword);
            }
        });

    }
    /**
     * @Author yangfeng
     * @Description //配置拦截请求资源
     * @Date 15:46 2022/11/23
     * @param http:
     * @return void
     * authorize:批准、许可、授权的意思，检查并确定身份是否有访问特定资源的权限
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
                .antMatchers("/login").permitAll()//设置不拦截登录请求
                .antMatchers("/**").fullyAuthenticated().and()
                .formLogin().loginPage("/login")
                .successHandler(successHandler).failureHandler(failureHandler)
                .and().csrf().disable();

    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
