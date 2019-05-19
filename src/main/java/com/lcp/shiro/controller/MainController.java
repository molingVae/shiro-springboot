package com.lcp.shiro.controller;

import com.lcp.shiro.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 * author: 沫凌
 * create: 2019-05-18 19:29
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "user/add";
    }

    @GetMapping("/update")
    public String update() {
        return "user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/401")
    public String no() {
        return "401";
    }

    @RequestMapping("/login")
    public String login(User user, Model model) {
        //创建subjext
        Subject subject = SecurityUtils.getSubject();
        //在认证提交之前准备token
        UsernamePasswordToken token = new
                UsernamePasswordToken(user.getUsername(), user.getPassword(),user.getRememberMe());

        try {
            subject.login(token);
            return "forward:/";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "username not exist");
            return "forward:/toLogin";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "password error");
            return "forward:/toLogin";

        }

    }

}
