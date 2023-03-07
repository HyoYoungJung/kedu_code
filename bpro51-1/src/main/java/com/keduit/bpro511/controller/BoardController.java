package com.keduit.bpro511.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
	
	@GetMapping("/board")
	public String[] board() {
		return new String[] {"board", "list"};
	}
}
