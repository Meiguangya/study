package com.cool.security.filter;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.cool.common.out.ApiResult;
import com.cool.util.MultiReadHttpServletRequest;
import com.cool.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义认证校验过滤器
 * 使用jwt
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {


    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        this.setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException {

        Authentication authentication=null;
        UsernamePasswordAuthenticationToken authRequest=null;
        try {
            MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(req);
            // 将前端传递的数据转换成jsonBean数据格式
            User user = JSONObject.parseObject(wrappedRequest.getBodyJsonStrByJson(wrappedRequest), User.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }

        authentication = authRequest;
        return this.getAuthenticationManager().authenticate(authentication);
    }


    /**
     * 验证成功后 派发token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain
        chain, Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();
        //派发jwt token
        String token = "123-token";
        ResponseUtils.out(response, ApiResult.ok("登录成功!", token));
    }

}
