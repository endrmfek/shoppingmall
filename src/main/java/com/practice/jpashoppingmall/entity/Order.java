package com.practice.jpashoppingmall.entity;

import com.practice.jpashoppingmall.auditor.BaseEntity;
import com.practice.jpashoppingmall.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 테이블
 * */

@Entity
@Table(name="orders") // order는 키워드로 이미 있음.
@Getter
@Setter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    private LocalDateTime orderDate; //주문 날자

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문 상태

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , orphanRemoval = true) // OrderItem 객체에 order 필드
    private List<OrderItem> orderItems = new ArrayList<>();

//    private LocalDateTime regTime;
//
//    private LocalDateTime updateTime;
}
