package com.firstproject.drawingcommunity.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = null;

        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호가 틀립니다.";
            System.out.println(errorMessage);
        } else if(exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 계정입니다.";
            System.out.println(errorMessage);
        } else {
            errorMessage = "알 수없는 오류입니다.";
            System.out.println(errorMessage);
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("/login?exception=" + errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
