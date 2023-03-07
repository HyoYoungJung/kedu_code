package com.keduit.bpro52.service;

import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;
import com.keduit.bpro52.repository.BoardRepository;
import com.keduit.bpro52.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

	private final BoardRepository repository;
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(BoardDTO dto) { //등록
		
		log.info("dto--------------------" + dto);
		
		Board board = dtoToEntity(dto);
		
		repository.save(board);
		
		return board.getBno();
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) { //리스트 조회

		log.info("pageRequestDTO : " + pageRequestDTO);

		Function<Object[], BoardDTO> fn = (en -> 
				entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));

//		Page<Object[]> result = repository.getBoardWithReplyCount(
//				pageRequestDTO.getPageable(Sort.by("bno").descending()));
		Page<Object[]> result = repository.searchPage(
				pageRequestDTO.getType(),
				pageRequestDTO.getKeyword(),
				pageRequestDTO.getPageable(Sort.by("bno").descending()));
		
		return new PageResultDTO<>(result, fn);
	}

	@Override
	public BoardDTO get(Long bno) { //한건 읽어오기 
		
		Object result = repository.getBoardByBno(bno);
		
		Object[] arr = (Object[]) result;
		
		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}

	@Transactional
	@Override
	public void removeWithReplies(Long bno) { //삭제
		replyRepository.deleteByBno(bno);
		repository.deleteById(bno);
	}
	
	@Transactional
	@Override
	public void modify(BoardDTO boardDTO) { 
		
		Board board = repository.getById(boardDTO.getBno());
		
		if(board != null) {
			board.changes(boardDTO.getTitle(), boardDTO.getContent());
		}
		
		repository.save(board);
	}
	
	
}
