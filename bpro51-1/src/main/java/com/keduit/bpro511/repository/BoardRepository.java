package com.keduit.bpro511.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro511.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
//	List<Board> findByBnoBetweenOrderByBnoDesc(Long from, Long to);
//	Page<Board> findByBnoBetween(Long from, Long to, Pageable pageable);
//	void deleteBoardByBnoLessThan(Long num);
	
	@Query("select b from Board b order by b.bno desc")
	List<Board> getListDesc();
	
	
	

}
