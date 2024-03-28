package com.deep.night.repository;

import com.deep.night.board.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Market, Integer> {
}
