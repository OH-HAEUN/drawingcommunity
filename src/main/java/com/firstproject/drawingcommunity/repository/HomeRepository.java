package com.firstproject.drawingcommunity.repository;

import com.firstproject.drawingcommunity.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<Board, Integer> {
    
}
