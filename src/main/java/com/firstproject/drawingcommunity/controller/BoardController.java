package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.Board;
import com.firstproject.drawingcommunity.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/write")
    public String boardwrite() {

        return "/boards/boardwrite";
    }

    @PostMapping("/writepro")
    public String boardWritePro(Board board, Model model, HttpServletRequest request, MultipartFile file) throws Exception {

        if (request.getParameter( "title" ) == "") {
            model.addAttribute( "message", "제목이 입력되지 않아 글을 등록할 수 없습니다." );
        } else if (request.getParameter( "content" ) == "") {
            model.addAttribute( "message", "내용이 입력되지 않아 글을 등록할 수 없습니다." );

        } else {
            model.addAttribute( "message", "글 작성이 완료되었습니다." );
            model.addAttribute("searchUrl", "/board/list");
            boardService.write(board, file);
        }

        return "/boards/message";
    }

    @GetMapping("/list")
    public String boardlist(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/boards/boardlist";
    }

    @GetMapping("/view")    // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "/boards/boardview";
    }

    @GetMapping("/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "/boards/boardmodify";
    }

    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file, HttpServletRequest request) throws Exception {

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        if (request.getParameter( "title" ) == "") {
            model.addAttribute( "message", "제목이 입력되지 않아 글을 수정할 수 없습니다." );
        } else if (request.getParameter( "content" ) == "") {
            model.addAttribute( "message", "내용이 입력되지 않아 글을 수정할 수 없습니다." );

        } else {
            model.addAttribute( "message", "글 수정이 완료되었습니다." );
            model.addAttribute( "searchUrl", "/board/list" );
            boardService.write( boardTemp, file);
        }

        return "/boards/message";
    }
}
