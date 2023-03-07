package com.keduit.bpro54.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testEncode() {
		String password = "1111";
		String enPw = passwordEncoder.encode(password); // password 1111을 인코딩
		System.out.println("enPw : " + enPw);
		
		boolean matchResult = passwordEncoder.matches(password, enPw); //pw와 인코딩pw가 일치하는가?
		
		System.out.println("matchResult : " + matchResult);
	}
	
	
}
