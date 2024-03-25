package com.firstproject.drawingcommunity.service;

import com.firstproject.drawingcommunity.entity.CustomUserDetails;
import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            return null;
        }else {
            return new CustomUserDetails(user);
        }
    }
}
