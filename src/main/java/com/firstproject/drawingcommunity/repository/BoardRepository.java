package com.firstproject.drawingcommunity.repository;

import com.firstproject.drawingcommunity.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
