package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
