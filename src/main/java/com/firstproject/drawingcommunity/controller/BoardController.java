package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.Board;
import com.firstproject.drawingcommunity.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String boardwrite() {

        return "/boards/boardwrite";
    }

    @PostMapping("/writePro")
    public String boardWritePro(Board board) {
        boardService.write(board);
        return "";
    }

    @GetMapping("/list")
    public String boardlist(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "/boards/boardlist";
    }

    @GetMapping("/view")    // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "/boards/boardview";
    }
}
