package com.keduit.bpro52.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> { //JpaRepository<테이블명, PK>

	@Modifying
	@Query("delete from Reply r where r.board.bno = :bno")
	void deleteByBno(Long bno);
	
	//게시물로 댓글 가져오기
	List<Reply> getRepliesByBoardOrderByRno(Board board);
}
