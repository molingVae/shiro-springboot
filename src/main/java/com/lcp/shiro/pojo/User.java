package com.lcp.shiro.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * description:
 * author: 沫凌
 * create: 2019-05-18 19:47
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;

    private Boolean rememberMe=false;
    private String perms;
    private String role;
}
