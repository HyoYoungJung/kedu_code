package com.keduit.bpro52.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro52.entity.Member;

@SpringBootTest
public class MemberRepositoryTests {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void insertMember() { //멤버 추가
		
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder()
					.email("user" + i + "@abc.com")
					.password("0000")
					.name("user"+i)
					.build();
			memberRepository.save(member);
		});
	}
}
