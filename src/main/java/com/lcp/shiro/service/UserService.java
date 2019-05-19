package com.lcp.shiro.service;

import com.lcp.shiro.mapper.UserMapper;
import com.lcp.shiro.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * author: 沫凌
 * create: 2019-05-19 16:21
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean insertUser(User user) {

        //使用用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        //MD5加密
        String newPassword = new SimpleHash("MD5", user.getPassword(), salt, 1024).toHex();
        user.setPassword(newPassword);
        log.info("[用户注册：]" + user.toString());
        int i = userMapper.insertUser(user);
        if (i > 0) {
            return true;
        }
        return false;

    }

}
