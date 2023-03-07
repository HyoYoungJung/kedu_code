package com.keduit.bpro52.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.bpro52.dto.ReplyDTO;
import com.keduit.bpro52.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
	
	private final ReplyService replyService;
	
	@GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> 
			getListByBoard(@PathVariable("bno") Long bno) {
		
		log.info("bno : " + bno);
		
		return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
	}
	
	@PostMapping("") //등록
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
	
	log.info("controller....." + replyDTO);
	Long rno = replyService.register(replyDTO);
	return new ResponseEntity<Long>(rno, HttpStatus.OK);
	}
	
	@DeleteMapping("/{rno}") //삭제
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		log.info("rno : " + rno);
		
		replyService.remove(rno);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PutMapping("/{rno}") //수정
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){

		log.info(replyDTO);
		
		replyService.modify(replyDTO);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
