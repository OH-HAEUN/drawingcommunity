package com.firstproject.drawingcommunity.service;

import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        System.out.println(user);
        System.out.println(user.getPassword());
        userRepository.save(user);
    }
//    로그인
//    public boolean login(User user, String password) {
//        User findUser = userRepository.findByUsername(user.getUsername());
//        System.out.println(findUser);
//        System.out.println(password);
//
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        if(findUser == null){
//            System.out.println("아이디틀림");
//            return false;
//        }
//
//        if(!passwordEncoder.matches(password, findUser.getPassword())) {
//            System.out.println("비번틀림");
//            return false;
//        }
//        return true;
//    }

    public User userInfo(String username) {
        return userRepository.findByUsername(username);
    }
}
