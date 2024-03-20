package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {

        return "home";
    }
}
