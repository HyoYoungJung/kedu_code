package com.keduit.bpro51.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO { //화면에 페이지 클릭 파라미터, 사이즈(페이지당 몇개)
	
	private int page;
	private int size;
	
	private String type;
	private String keyword;
	
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page -1, size, sort); //부트에 설정된 페이지 숫자 -1 해줘야 뷰에서 제대로 나타난다(?) 
	}
}
