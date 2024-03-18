package com.firstproject.drawingcommunity.service;

import com.firstproject.drawingcommunity.entity.Board;
import com.firstproject.drawingcommunity.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
    }
}
