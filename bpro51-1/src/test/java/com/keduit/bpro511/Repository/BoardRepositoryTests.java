package com.keduit.bpro511.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro511.entity.Board;
import com.keduit.bpro511.repository.BoardRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
	
	@Autowired //필드주입
	BoardRepository boardRepository;
	
//테스트
	@Test 
	public void testClass() {
		System.out.println(boardRepository.getClass().getName());
	}
	
//Create 등록 (Insert) 
	@Test 
	public void testInsertDummies() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			Board board = Board.builder()
								.title("제목"+i)
								.content("내용"+i)
								.writer("길동"+i).build();
			boardRepository.save(board);
		});
	}
	
//Read 조회 (지정한 번호 하나만)
	@Test
	public void testSelect() { //쿼리문 실행후 sysout 출력됨(쿼리문 아래에 줄)
		
		Long bno = 50L;
		Optional<Board> result = boardRepository.findById(bno);
		
		System.out.println("--------------------");
		if(result.isPresent()) {
			Board board = result.get();
			System.out.println(board);
		}
	}
	
//	//지정한 번호 하나만 조회 (getOne 메서드는 사용종료)
//	@Transactional
//	@Test
//	public void testSelect2() { //실행하면 sysout 줄이 맨 위에 나옴(sql문 수행 전)
//		
//		Long bno = 100L;
//		Board board = boardRepository.getOne(bno);
//				
//		System.out.println("--------------------");
//		System.out.println(board);
//	}
		
	//Read 조회 (리스트 전체)
	@Transactional
	@Test
	public void testSelectQuery() { //쿼리문 실행후 sysout 출력됨(쿼리문 아래에 줄)
		
		List<Board> list = boardRepository.getListDesc(); //리스트 내림차순(큰->작)
		
		System.out.println("--------------------");
		for(Board board : list) {
			System.out.println(board);
		}
	}

//Update 수정
	@Test
	public void testUpdate() {
		Board board = Board.builder()
							.bno(110L)
							.title("수정한 제목")
							.content("수정한 내용")
							.writer("수정한 작성자").build();
		System.out.println(boardRepository.save(board));
	}
	
//Delete 삭제
	@Test
	public void testDelete() {
		Long bno = 100L;
		boardRepository.deleteById(bno);
	}

//페이징 처리	
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
