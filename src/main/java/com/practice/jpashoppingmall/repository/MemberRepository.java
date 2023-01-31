package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
