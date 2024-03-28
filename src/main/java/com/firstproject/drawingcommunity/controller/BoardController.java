package com.firstproject.drawingcommunity.controller;

import com.firstproject.drawingcommunity.entity.Board;
import com.firstproject.drawingcommunity.entity.User;
import com.firstproject.drawingcommunity.service.BoardService;
import com.firstproject.drawingcommunity.service.UserService;
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

import java.security.Principal;

@Controller
@RequestMapping("/")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

    @GetMapping("/write")
    public String boardWrite() {

        return "/boards/boardwrite";
    }

    @PostMapping("/writepro")
    public String boardWritePro(Board board, Model model, HttpServletRequest request, MultipartFile file, Principal principal) throws Exception {

        String writer = principal.getName();
        User user = userService.userInfo(writer);

        if (request.getParameter( "title" ) == "") {
            model.addAttribute( "message", "제목이 입력되지 않아 글을 등록할 수 없습니다." );
        } else if (request.getParameter( "content" ) == "") {
            model.addAttribute( "message", "내용이 입력되지 않아 글을 등록할 수 없습니다." );

        } else {
            model.addAttribute( "message", "글 작성이 완료되었습니다." );
            model.addAttribute("searchUrl", "/list");
            boardService.write(board, file, user.getNickname());
        }

        return "/message";
    }

    @GetMapping("/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Board> list = null;

        if (searchKeyword == null) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

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
        boardService.updateView(id);
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
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file, HttpServletRequest request, Principal principal) throws Exception {

        String writer = principal.getName();
        User user = userService.userInfo(writer);


        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        if (request.getParameter( "title" ) == "") {
            model.addAttribute( "message", "제목이 입력되지 않아 글을 수정할 수 없습니다." );
        } else if (request.getParameter( "content" ) == "") {
            model.addAttribute( "message", "내용이 입력되지 않아 글을 수정할 수 없습니다." );

        } else {
            model.addAttribute( "message", "글 수정이 완료되었습니다." );
            model.addAttribute( "searchUrl", "/list" );
            boardService.write( boardTemp, file, user.getNickname());
        }

        return "/message";
    }
}
