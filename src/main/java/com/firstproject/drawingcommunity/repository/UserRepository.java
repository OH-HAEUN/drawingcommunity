package com.firstproject.drawingcommunity.repository;

import com.firstproject.drawingcommunity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
