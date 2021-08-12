package com.dhkj.playonline.controller;


import com.dhkj.playonline.pojo.User;
import com.dhkj.playonline.service.UserService;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String login(@RequestParam("error") String error, Model model) {
        model.addAttribute("error",error);
        return "funPage/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        response.addCookie(new Cookie("JSESSIONID",null));
        return "index";
    }

    @RequestMapping("/toRegister")
    public String toRegister(Model model) {
        System.out.println(1111);
        model.addAttribute("msg",0);
        return "funPage/register";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("mail") String mail,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("RePassword") String RePassword, Model model) {
        System.out.println(2222);
        if (!password.equals(RePassword)) {
            model.addAttribute("msg",1);
            return "funPage/register";
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEnble(1);
            user.setEmail(mail);
            user.setRoles("ROLE_vip1");
            userService.insertAUser(user);
            return "redirect:/toLogin?error=false";
        }
    }

    @RequestMapping("/toChange")
    public String toChangePassword(Model model) {
        System.out.println(123);
        model.addAttribute("msg",0);
        return "funPage/changePassword";
    }


    @RequestMapping("/change")
    public String changePassword(Model model,
            @RequestParam("mail") String mail,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("RePassword") String repass) {
        if (!password.equals(repass)) {
            model.addAttribute("msg",1);
            return "funPage/changePassword";
        } else if (userService.selectUserByMailAndUsername(mail,username)==null){
            model.addAttribute("msg",2);
            return "funPage/changePassword";
        } else {
            userService.updatePassword(username,password);
            return "redirect:/toLogin?error=false";
        }
    }
}
