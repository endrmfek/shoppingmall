package com.practice.jpashoppingmall;

import com.practice.jpashoppingmall.constant.Role;
import com.practice.jpashoppingmall.entity.Member;
import com.practice.jpashoppingmall.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JpashoppingmallApplication {



	public static void main(String[] args) {
		SpringApplication.run(JpashoppingmallApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner run(MemberRepository memberRepository , PasswordEncoder passwordEncoder) {
		return (String[] arg) -> {Member member = new Member();
		member.setName("관리자");
		member.setEmail("1234@1234.com");
		member.setAddress("1234");
		String password = passwordEncoder.encode("1234");
		member.setPassword(password);
		member.setRole(Role.ADMIN);
		memberRepository.save(member);
		};
	}*/


}
