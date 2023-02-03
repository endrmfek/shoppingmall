package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.dto.ItemSearchDto;
import com.practice.jpashoppingmall.dto.MainItemDto;
import com.practice.jpashoppingmall.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    //아이템 목록
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    //메인화면
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto , Pageable pageable);
}
