package com.practice.jpashoppingmall.entity;

import com.practice.jpashoppingmall.auditor.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 장바구니 엔티티
 * */

@Entity
@Table(name="cart")
@Setter
@Getter
@ToString
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // 컬럼 이름 설정 (생략 시 jpa가 알아서 처리)
    private Member member;

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }
}
