package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

//첫번째에 클래스 타입, 두번째에 기본키 타입
public interface ItemRepository extends JpaRepository<Item, Long> , QuerydslPredicateExecutor<Item> {

    //자동으로 코드 생성 -> JpaRepository 뜯어봐
    List<Item> findByItemName(String itemName);

    //nativeQuery 옵션도 사용 가능하지만 데이터베이스에 독립적이라는 장점이 사라짐.
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);



}

