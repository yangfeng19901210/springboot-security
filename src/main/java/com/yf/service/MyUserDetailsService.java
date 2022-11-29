package com.yf.service;

import com.yf.entity.Permission;
import com.yf.entity.User;
import com.yf.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: springboot-security
 * @package: com.yf.service
 * @className: MyUserDetailsService
 * @author: yangfeng
 * @description: TODO
 * @date: 2022/11/28 15:34
 * @version: 1.0
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        //根据用户名查询对应的权限
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<Permission> permissions = userMapper.findPermissionByUsername(username);
        log.info("用户名 {} 对应的权限为 {}",username,permissions.toString());
        if(null!=permissions && permissions.size()>0){
            for (Permission permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
            }
        }
        user.setAuthorities(authorities);
        return user;
    }
}
