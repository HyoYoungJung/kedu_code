package com.keduit.bpro51.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.bpro51.dto.BoardDTO;
import com.keduit.bpro51.dto.PageRequestDTO;
import com.keduit.bpro51.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller //나 컨트롤러야~~
@RequestMapping("/board") //board로 들어오면 내가 처리(매핑)할게~~
@Log4j2
@RequiredArgsConstructor //BoardService 생성자 주입 받음
public class BoardController {

	private final BoardService service; //@RequiredArgsConstructor로 생성자 주입
	
	@GetMapping("/") // 루트(/)로 들어오면 list로 뿌려줄거야(리턴)~~
	public String index() {
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		
		log.info("list...............", pageRequestDTO);
		model.addAttribute("result", service.getList(pageRequestDTO));
	}
	
	@GetMapping("/register")
	public void register() {
		log.info("register get..........");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		log.info("register dto.........." + dto);
		
		Long bno = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(long bno, 
			@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("bno: " + bno);
		
		BoardDTO dto = service.read(bno);
		model.addAttribute("dto",dto);
	}
	
	@PostMapping("/remove")
	public String remove(long bno, RedirectAttributes redirectAttributes) {
		log.info("----- remove bno : " + bno);
		
		service.remove(bno);
		redirectAttributes.addFlashAttribute("msg", bno); //n번이 삭제되었습니다. 창이 뜨게끔 스크립트의 msg 사용.
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO dto, 
						 @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
						 RedirectAttributes redirectAttributes) {
		
		log.info("post modify............");
		log.info("dto : ", dto);
		
		service.modify(dto);
		
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("bno", dto.getBno());
		redirectAttributes.addAttribute("type", requestDTO.getType());
		redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
		return "redirect:/board/read";
	}
	
	
}
