package com.keduit.bpro52.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.keduit.bpro52.dto.ReplyDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Reply;
import com.keduit.bpro52.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //생성자 주입 받음 - replyRepository
public class ReplyServiceImpl implements ReplyService {

	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
		
		return reply.getRno();
	}

	@Override
	public List<ReplyDTO> getList(Long bno) {
		
		List<Reply> result = 
				replyRepository.getRepliesByBoardOrderByRno(
						Board.builder().bno(bno).build());
		return result.stream().map(reply -> entityToDTO(reply))
				.collect(Collectors.toList());
	}

	@Override
	public void modify(ReplyDTO replyDTO) {
		
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
	}

	@Override
	public void remove(Long rno) {
		replyRepository.deleteById(rno);
	}

}
