package com.firstproject.drawingcommunity.service;

import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void user(User user, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        System.out.println(user);
        System.out.println(user.getPassword());
        userRepository.save(user);
    }
}
