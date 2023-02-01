package com.practice.jpashoppingmall.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Audit : 감시하다
 * 공통 속성 관리
 * */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //등록자와 수정자 -> 유저 이름을 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if(authentication != null) {
            userId = authentication.getName();
        }
        return Optional.of(userId);
    }
}
