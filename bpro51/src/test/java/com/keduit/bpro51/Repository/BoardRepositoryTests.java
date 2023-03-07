package com.keduit.bpro51.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro51.entity.Board;
import com.keduit.bpro51.repository.BoardRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
	
	@Autowired //필드주입
	private BoardRepository boardRepository;

//Insert 추가
	@Test 
	public void testInsert() {
		IntStream.rangeClosed(1, 20).forEach(i -> {
			Board board = Board.builder()
								.title("title..."+i)
								.content("content..."+i)
								.writer("user"+(i % 10))
								.build();
			
			Board result = boardRepository.save(board);
			log.info("BNO: " + result.getBno()); //Log4j2 import 해야 사용됨
		});
	}
	
//Read 조회 (지정한 번호 하나만)
	@Test
	public void testSelect() { //쿼리문 실행후 sysout 출력됨(쿼리문 아래에 줄)
		Long bno = 10L;
		
		Optional<Board> result = boardRepository.findById(bno);
		
		Board board = result.orElseThrow();
		
		log.info(board);
	}
	
//Update 수정
	@Test
	public void testUpdate() {
		
		Long bno = 11L;
		
		Optional<Board> result = boardRepository.findById(bno);
				
		Board board = result.orElseThrow();
		
		board.changes("update..title 11", "update content 11");
		
		boardRepository.save(board);
	}
	
//Delete 삭제
	@Test
	public void testDelete() {
		Long bno = 1L;
		
		boardRepository.deleteById(bno);
	}
	
//Paging 게시판 페이지	
	@Test
	public void testPaging() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Board> result = boardRepository.findAll(pageable);
		
		log.info("total count : " + result.getTotalElements());
		log.info("total page : " + result.getTotalPages());
		log.info("total number : " + result.getNumber());
		log.info("total size : " + result.getSize());
		
		//목록 출력
		List<Board> boardlist = result.getContent();
		
		boardlist.forEach(board -> log.info(board));
	}
	
	
	
}
