package com.lcp.shiro.mapper;

import com.lcp.shiro.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * description:
 * author: 沫凌
 * create: 2019-05-18 20:00
 */
@Mapper
public interface UserMapper {

    @Select("select * from shirouser where username=#{name}")
    User getUserByName(@Param("name") String name);
    @Insert("insert into shirouser(username,password,perms,role) values(#{username},#{password},#{perms},#{role})")
    int insertUser(User user);
}
