package com.ssm.controller;

import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //登录功能
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, Model model, HttpServletRequest request){
        User loginuser = userService.login(user);
        if(loginuser!=null){
            request.getSession().setAttribute("loginUser",loginuser);
            model.addAttribute("userName",user.userName);
            return "main";
        }
        else {
            model.addAttribute("error","账号或密码错误！");
            return "index";
        }
    }

    //跳转登录页面
    @RequestMapping(value = "/to/login",method = RequestMethod.GET)
    public String toLogin(Model model){
        model.addAttribute("error","您还没有登录，请先登录!");
        return "index";
    }

    //跳转首页
    @RequestMapping("/toMainPage")
    public String toMainPage(){
        return "main";
    }

    //注销登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        try {
            HttpSession session=request.getSession();
            //销毁session
            session.invalidate();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "index";
    }

}
