package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping("/")
    public String home(Model model, @PageableDefault(page = 0, size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Principal principal) {
        model.addAttribute("list", homeService.homeFreeBoard(pageable));

        if (principal != null) {
            model.addAttribute( "loginuser", principal.getName() );
        };

        return "home";
    }
}
