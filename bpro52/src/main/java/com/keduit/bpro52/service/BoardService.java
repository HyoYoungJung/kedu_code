package com.keduit.bpro52.service;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;

public interface BoardService {
	
	Long register(BoardDTO dto); //등록
	
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO); //리스트 조회
	
	BoardDTO get(Long bno); //한건 읽어오기 
	
	void removeWithReplies(Long bno); //삭제
	
	void modify(BoardDTO boardDTO); //수정(업데이트)
	
	default Board dtoToEntity(BoardDTO dto) {
		
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return board;
	}

	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
		
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.updateDate(board.getUpdateDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue()) // Long -> int
				.build();
				return boardDTO;
	}

}
