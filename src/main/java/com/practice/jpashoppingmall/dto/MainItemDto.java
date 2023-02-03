package com.practice.jpashoppingmall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {
    private Long id;

    private String itemName;

    private String itemDetail;

    private String imageUrl;

    private Integer price;

    /**
     * Querydls로 결과 조회 시 DTO 객체로 바로 받아옴
     * */
    @QueryProjection
    public MainItemDto(Long id, String itemName, String itemDetail, String imageUrl, Integer price) {
        this.id = id;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
