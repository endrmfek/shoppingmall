package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.dto.CartDetailDto;
import com.practice.jpashoppingmall.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.practice.jpashoppingmall.dto.CartDetailDto(ci.id, i.itemName, i.price, ci.count, im.imageUrl) " +
            "from CartItem ci, ItemImage im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repImageYn = 'Y' " +
            "order by ci.regTime desc ")
    List<CartDetailDto> findCartDetailDtoList(@Param("cartId") Long cartId);


}
