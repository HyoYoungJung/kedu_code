package com.keduit.bpro51.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro51.dto.BoardDTO;
import com.keduit.bpro51.dto.PageRequestDTO;
import com.keduit.bpro51.dto.PageResultDTO;
import com.keduit.bpro51.entity.Board;

@SpringBootTest
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testRegister() { //등록
		BoardDTO boardDTO = BoardDTO.builder()
				.title("sample title...." )
				.content("sample content....")
				.writer("user01")
				.build();
		
		System.out.println(service.register(boardDTO));
	}

	@Test
	public void testList() { //리스트 조회 - 내림차순으로 1페이지, 10개(=size) 
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<BoardDTO, Board> resultDTO = service.getList(pageRequestDTO);
		
		System.out.println("PREV : " + resultDTO.isPrev());
		System.out.println("NEXT : " + resultDTO.isNext());
		System.out.println("TOTAL : " + resultDTO.getTotalPage());
		System.out.println("-------------------------------------------------------------");
		
		for(BoardDTO boardDTO : resultDTO.getDtoList()) {
			System.out.println(boardDTO);
		}
		System.out.println("=============================================================");
		resultDTO.getPageList().forEach(i -> System.out.println(i));
	}

	@Test
	public void testSearch() { //검색
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.page(1)
				.size(10)
				.type("tc") //title & content 중
				.keyword("sample") //sample이 들어간 것 검색
				.build();

		PageResultDTO<BoardDTO, Board> resultDTO = service.getList(pageRequestDTO);
		
		System.out.println("PREV: " + resultDTO.isPrev());
		System.out.println("NEXT: " + resultDTO.isNext());
		System.out.println("TOTAL: " + resultDTO.getTotalPage());
		
		System.out.println("--------------------------");
		for(BoardDTO dto : resultDTO.getDtoList()) {
			System.out.println("DTO: " + dto);
		}
		System.out.println("===========================");
		resultDTO.getPageList().forEach(i -> System.out.println(i));
	}
}

