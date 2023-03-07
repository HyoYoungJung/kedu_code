package com.keduit.bpro52.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister() { //등록
		
		BoardDTO dto = BoardDTO.builder()
				.title("title test....")
				.content("content test....")
				.writerEmail("user100@abc.com") // member tbl에 있는 메일을 넣어야 에러안남
				.build();
		
		Long bno = boardService.register(dto);
		
		System.out.println(bno + ":" + dto);
	}
	
	@Test
	public void testList() { //리스트 조회
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		
		PageResultDTO<BoardDTO, Object[]> result = 
				boardService.getList(pageRequestDTO);
		
		for(BoardDTO boardDTO : result.getDtoList()) {
			System.out.println(boardDTO);
		}
	}
	
	@Test
	public void testGet() { //한건 읽어오기 
		Long bno = 97L;
		BoardDTO boardDTO = boardService.get(bno);
		System.out.println("testGet...." + boardDTO);
	}
	
	@Test
	public void testRemove() {
		
		Long bno = 1L;
		
		boardService.removeWithReplies(bno);
	}
	
	@Test
	public void testModify() {
		
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(2L)
				.title("title update....")
				.content("content update....")
				.build();
		
		boardService.modify(boardDTO);
	}
	
	
	
}
