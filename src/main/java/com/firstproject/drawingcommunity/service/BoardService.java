package com.firstproject.drawingcommunity.service;

import com.firstproject.drawingcommunity.entity.Board;
import com.firstproject.drawingcommunity.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 작성 처리
    public void write(Board board, MultipartFile file, String writer, String writerid) throws Exception {

        String projectPath = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();

        if (file != null) {
            if (file.getOriginalFilename() != "") {
                String fileName = uuid + "_" + file.getOriginalFilename();

                File saveFile = new File( projectPath, fileName );

                file.transferTo( saveFile );

                board.setFilename( fileName );
                board.setFilepath( "/files/" + fileName );
            }
        }
        System.out.println(writerid);
        board.setWriter( writer );
        board.setWriter_id( writerid );

        boardRepository.save( board );
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll( pageable );
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining( searchKeyword, pageable );
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {

        return boardRepository.findById( id ).get();
    }

    @Transactional
    public int updateView(Integer id) {
        return boardRepository.updateView(id);
    }


    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById( id );
    }
}
