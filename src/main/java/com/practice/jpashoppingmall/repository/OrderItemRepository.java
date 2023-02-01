package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {

}
