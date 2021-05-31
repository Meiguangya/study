package com.cool.security;

import com.cool.security.filter.JWTAuthFilter;
import com.cool.security.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author water33
 */
@Configuration
@EnableWebSecurity
@SuppressWarnings("all")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String loginUrl = "/login";
    private final String logoutUrl = "/logout";


    /**
     * 权限配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/**").and()
            //登录配置
            .formLogin().usernameParameter("username").passwordParameter("password").loginPage(loginUrl).and()
            //自定义认证filter
            .addFilterBefore(new JWTLoginFilter(loginUrl, this.authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            //自定义授权filter
            .addFilterBefore(new JWTAuthFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    //自定义退出登录处理类
    public class MyLogoutHandler implements LogoutHandler {
        @Override
        public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
            String token = "";//todo request.getHeader();
            //todo remove token
        }
    }

    //退出成功处理类
    public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            super.onLogoutSuccess(request, response, authentication);
        }
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder：Spring Security 提供的加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder();
    }


}
