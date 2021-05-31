package com.cool.security;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.cool.dto.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        //自定义的验证
        UserDetails userDetails = this.myAuthenticate(authentication);
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    private UserDetails myAuthenticate(Authentication authentication){
        UserDetails userDetails = null;
        try {
            String username = (String) authentication.getPrincipal();
            String password = (String) authentication.getCredentials();
            //SystemUsers systemUser = this.getUserByName(username);
            SecurityUser systemUser = new SecurityUser();
            if (systemUser == null) {
                throw new RuntimeException("用户名:" + username + "不存在");
            }
//            boolean isSucceed = DigestUtil.bcryptCheck(password, systemUser.getPassword());
//            if (isSucceed == false) {
//                throw new RuntimeException("密码不正确!");
//            }
            //iSystemUsersService.update(new SystemUsers(), new UpdateWrapper<SystemUsers>().eq("username", username).set("last_login", DateUtil.formatDateTime(new Date())));

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //todo 给账号配置权限角色
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("a");
            grantedAuthorities.add(authority);

            return new User(username, DigestUtil.bcrypt(password), grantedAuthorities);
        } catch (Exception e) {
            //log.error("errors:{}", e);
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
