package com.keduit.bpro52.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTests {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Test
	public void insertReply() {
		
		IntStream.rangeClosed(1, 300).forEach(i -> {
			long bno = (long)(Math.random()*100) + 1; //1~100중 랜덤 가져옴
			Board board = Board.builder().bno(bno).build();
			
			Reply reply = Reply.builder()
					.text("reply......" + i)
					.board(board)
					.replyer("guest" + i)
					.build();
			replyRepository.save(reply);
		});
	}
	
	@Test
	public void readReply() { // 댓글 조회
		
		Optional<Reply> result = replyRepository.findById(1L);
		
		Reply reply = result.get();
		
		System.out.println(reply);
		System.out.println(reply.getBoard());
	}

	@Test
	public void testListByBoard() { //게시글에 달린 댓글리스트 
		List<Reply> replyList = 
				replyRepository.getRepliesByBoardOrderByRno(
						Board.builder().bno(96L).build());
		replyList.forEach(reply -> System.out.println(reply));
	}
	
	
}
