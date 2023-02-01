package com.practice.jpashoppingmall.entity;


import com.practice.jpashoppingmall.auditor.BaseEntity;
import com.practice.jpashoppingmall.constant.Role;
import com.practice.jpashoppingmall.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "member")
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue // strategy default = AUTO
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto , PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(memberFormDto.getRole());

        return member;
    }



}

