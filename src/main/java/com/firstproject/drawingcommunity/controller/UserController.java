package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signUp() {
        return "/users/signup";
    }

    @PostMapping("/signuppro")
    public String signupPro(User user, Model model, HttpServletRequest request) {
        String password = request.getParameter("password");
        model.addAttribute("message", "회원가입 되었습니다.");
        model.addAttribute("searchUrl", "/");
        userService.signup(user, password);
        return "/message";
    }

    @GetMapping("/login")
    public String login(){
        return  "/users/login";
    }

//    @PostMapping("/login")
//    public String loginPro(User user, HttpServletRequest request){
//        String password = request.getParameter("password");
//        if(userService.login(user, password)){
//            System.out.println("됨?");
//            return "redirect:/";
//        }
//        return "/users/login";
//    }
}
