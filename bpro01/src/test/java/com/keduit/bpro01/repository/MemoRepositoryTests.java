package com.keduit.bpro01.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.keduit.bpro01.entity.Memo;

@SpringBootTest
public class MemoRepositoryTests {
	
	@Autowired  //필드주입받음
	MemoRepository memoRepository;
	
	@Test
	public void testClass() {
		System.out.println(memoRepository.getClass().getName());
	}
	
	//생성
	@Test
	public void testInsertDummies() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Memo memo = Memo.builder().memoText("Sample memo......." + i).build();
			memoRepository.save(memo);
		});
	}
	
	//조회
	@Test
	public void testSelect() { //실행하면 sysout 줄이 맨 아래에 나옴
		
		Long mno = 100L;
		Optional<Memo> result = memoRepository.findById(mno);
		
		System.out.println("--------------------");
		if(result.isPresent()) {
			Memo memo = result.get();
			System.out.println(memo);
		}
	}
	
	//조회
	@Transactional
	@Test
	public void testSelect2() { //실행하면 sysout 줄이 맨 위에 나옴(sql문 수행 전)
		
		Long mno = 100L;
		Memo memo = memoRepository.getOne(mno);
				
		System.out.println("--------------------");
		System.out.println(memo);
	}
	
	@Transactional
	@Test
	public void testSelectQuery() { //실행하면 sysout 줄이 맨 위에 나옴(sql문 수행 전)
		
//		Long mno = 100L;
		List<Memo>list = memoRepository.getListDesc();
				
		System.out.println("--------------------");
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	
	//삭제
	@Test  
	public void testDelete() {  
		Long mno = 100L;
		memoRepository.deleteById(mno);
	}

	//수정
	@Test
	public void testUpdate() {
		Memo memo = Memo.builder().mno(99L).memoText("Update text.......").build();
		System.out.println(memoRepository.save(memo));
	}
	
	//정렬 처리
	@Test
	public void testPageDefault() {
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<Memo> result = memoRepository.findAll(pageable);
		
		System.out.println(result);
		System.out.println("==========================");
		
		//여러 정보 꺼내 보기
		System.out.println("Total pages : " + result.getTotalPages());
		System.out.println("Total count : " + result.getTotalElements());
		System.out.println("Page Number : " + result.getNumber());
		System.out.println("Page size : " + result.getSize());
		System.out.println("has next page?" + result.hasNext());
		System.out.println("first page?" + result.isFirst());
	
		System.out.println("==========================");
		for (Memo memo : result.getContent()) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void testSort() {
		
		Sort sort1 = Sort.by("mno").descending();
		Sort sort2 = Sort.by("memoText").ascending();
		Sort sortAll = sort1.and(sort2);
		
		
//		Pageable pageable = PageRequest.of(0, 10, sort1);
		Pageable pageable = PageRequest.of(0, 10, sortAll);
		Page<Memo> result = memoRepository.findAll(pageable);
		
		result.get().forEach(memo -> {
			System.out.println(memo);
		});
	}

	@Test
	public void testQueryMethods() {
		
		List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(65L, 90L);
		
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
		
	@Test
	public void testQueryMethodWithPageable() {
			
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
			
		Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
		result.get().forEach(memo -> System.out.println(memo));
	}
	
	@Commit
	@Transactional
	@Test
	public void testDeleteQueryMethods() {
		memoRepository.deleteMemoByMnoLessThan(10L);
	}
	

	
	
	

}
