package com.keduit.bpro52.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro52.dto.ReplyDTO;

@SpringBootTest
public class ReplyServiceTests {

	@Autowired
	private ReplyService service;
	
	@Test
	public void testGetList() {
		
		Long bno = 96L;
		
		List<ReplyDTO> replyDTOList = service.getList(bno);
		
		replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
		
		
	}
}
