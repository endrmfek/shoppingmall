package com.practice.jpashoppingmall.dto;

import com.practice.jpashoppingmall.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * 조회 조건
 * 1. 상품 등록일
 * 2. 상품 판매 상태
 * 3. 상품명 또는 상품 등록자 아이디
 * */
@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";
}
