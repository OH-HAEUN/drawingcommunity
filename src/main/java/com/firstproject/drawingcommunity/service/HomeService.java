package com.firstproject.drawingcommunity.service;

import com.firstproject.drawingcommunity.entity.Board;
import com.firstproject.drawingcommunity.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;
    public Page<Board> homeFreeBoard(Pageable pageable){

        return homeRepository.findAll(pageable);
    }
}
