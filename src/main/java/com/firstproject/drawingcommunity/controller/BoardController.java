package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.Board;
import com.firstproject.drawingcommunity.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardwrite")
    public String boardwrite() {

        return "/boards/boardwrite";
    }

    @PostMapping("/writePro")
    public String boardWritePro(Board board) {
        boardService.write(board);
        return "";
    }
}
