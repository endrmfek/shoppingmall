package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {

    List<ItemImage> findByItemIdOrderByIdAsc(Long itemId);

    ItemImage findByItemIdAndRepImageYn(Long itemId, String repImageYn);
}
