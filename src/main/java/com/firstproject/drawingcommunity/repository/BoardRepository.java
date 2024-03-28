package com.firstproject.drawingcommunity.repository;

import com.firstproject.drawingcommunity.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    Page<Board> findByTitleContaining(String searchKeword, Pageable pageable);

    @Modifying
    @Query("update Board b set b.view = b.view + 1 where b.id = :id")
    int updateView(Integer id);
}
