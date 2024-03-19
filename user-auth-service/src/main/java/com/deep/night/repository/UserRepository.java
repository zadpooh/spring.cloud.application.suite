package com.deep.night.repository;

import com.deep.night.user.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Board, Integer> {
}
