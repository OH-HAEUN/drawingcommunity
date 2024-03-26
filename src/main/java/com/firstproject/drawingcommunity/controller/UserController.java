package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signUp() {
        return "/users/signup";
    }

//    @GetMapping("/usernameSameCheck")
//    public

    @GetMapping("/idcheck")
    public ResponseEntity<String> idCheck(@RequestParam("username") String username) throws BadRequestException {
        System.out.println(username);
        if (userService.userIdCheck(username) == true) {
            throw new BadRequestException("이미 사용중인 아이디 입니다.");
        } else {
            return ResponseEntity.ok( "사용 가능한 아이디 입니다." );
        }
    }

    @GetMapping("/nicknamecheck")
    public ResponseEntity<String> nicknameCheck(@RequestParam("nickname") String nickname) throws BadRequestException {
        System.out.println(nickname);
        if (userService.userNicknameCheck(nickname) == true) {
            throw new BadRequestException("이미 사용중인 닉네임 입니다.");
        } else {
            return ResponseEntity.ok( "사용 가능한 닉네임 입니다." );
        }
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
    public String login(Model model, @RequestParam(value="exception", required = false) String exception){
        System.out.println(exception);
        if (exception != null) {
            model.addAttribute( "exception", exception );
        };
        return  "/users/login";
    }

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
