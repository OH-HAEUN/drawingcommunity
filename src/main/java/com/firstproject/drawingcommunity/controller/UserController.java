package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;
import java.security.Principal;

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

    @GetMapping("/mypage")
    public String mypage(Model model, Principal principal) {
        String userid = principal.getName();
        User user = userService.userInfo(userid);

        if (user != null) {
            System.out.println(user);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("email", user.getEmail());
        }
        else {
            System.out.println("user = null");
        }
        return "/users/mypage";
    }
}
