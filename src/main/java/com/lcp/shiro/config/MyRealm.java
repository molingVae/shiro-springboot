package com.lcp.shiro.config;

import com.lcp.shiro.mapper.UserMapper;
import com.lcp.shiro.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * description:
 * author: 沫凌
 * create: 2019-05-18 19:16
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //资源授权
        //info.addStringPermission(user.getPerms());
        //角色授权
        HashSet<String> set = new HashSet<>();
        set.add(user.getRole());
        info.setRoles(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userMapper.getUserByName(token.getUsername());

        if (user == null) {
            return null;
        }
        log.info("[用户]" + user.toString());
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());

        return new SimpleAuthenticationInfo(user, user.getPassword(),salt, getName());
    }
}
