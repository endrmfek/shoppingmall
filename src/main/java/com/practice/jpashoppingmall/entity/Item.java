package com.practice.jpashoppingmall.entity;

import com.practice.jpashoppingmall.auditor.BaseEntity;
import com.practice.jpashoppingmall.constant.ItemSellStatus;
import com.practice.jpashoppingmall.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

//

@Entity // JPA
@Table(name = "item") // 생략 가능. 생략 시 클래스 이름과 같은 테이블 생성
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id
    @GeneratedValue // strategy 사용 가능.
    @Column(name = "item_id")
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50) // not null 설정
    private String itemName; //상품명

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고량

    @Lob
    @Column(nullable = false)
    private String itemDetail; // 재품 설명

    @Enumerated(EnumType.STRING)  // enum타입 설정.
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto) {
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }
}
