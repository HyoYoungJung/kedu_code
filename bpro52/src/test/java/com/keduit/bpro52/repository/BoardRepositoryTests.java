package com.keduit.bpro52.repository;

import java.util.Arrays;
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

import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;

@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void insertBoard() { //등록
		
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder().email("user" + i + "@abc.com").build();
			
			Board board = Board.builder()
					.title("Title....." + i)
					.content("Content....." + i)
					.writer(member)
					.build();
			
			boardRepository.save(board);
		});
	}
	
	@Transactional // fetchType이 lazy로딩일 때 추가
	@Test
	public void testRead() { //조회
		Optional<Board> result = boardRepository.findById(100L);
		
		Board board = result.get();
		
		System.out.println(board);
		System.out.println(board.getWriter());
	}
	
	@Test
	public void testReadWithWriter() { //조회 w.회원정보
		
		Object result = boardRepository.getBoardWithWriter(100L);
		Object[] arr = (Object[])result;
		
		System.out.println("------------------------");
		System.out.println(Arrays.toString(arr));
	}
	
	@Test
	public void testGetBoardWithReply() {  //조회 w.리플
		
		List<Object[]> result = boardRepository.getBoardWithReply(100L);
		
		for(Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}
	
	@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row -> {
			Object[] arr = row;
			
			System.out.println(Arrays.toString(arr));
		});
	}
	
	@Test
	public void testReadOne() { //하나만 읽음
		Object result = boardRepository.getBoardByBno(40L);
		
		Object[] arr = (Object[])result;
		
		System.out.println(Arrays.toString(arr));
	}
	
	@Test
	public void testSearch1() {
		boardRepository.search1();
	}
	
	@Test
	public void testSearchPage() { //검색
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepository.searchPage("tcw", "1", pageable);
	}
	
	@Test
	public void testSearchPageSort() { 
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending()
				.and(Sort.by("title").ascending()));
		Page<Object[]> result = boardRepository.searchPage("tcw", "1", pageable);
	}
	
}
