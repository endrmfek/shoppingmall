package com.practice.jpashoppingmall.repository;

import com.practice.jpashoppingmall.constant.ItemSellStatus;
import com.practice.jpashoppingmall.entity.Item;
import com.practice.jpashoppingmall.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {


    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        // given
        Item item = new Item(); //setter넣기 싫다..
        item.setItemName("테스트상품");
        item.setPrice(10000);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setItemDetail("테스트상품 상세설명");
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        // when
        Item savedItem = itemRepository.save(item);
        // then
        System.out.println(savedItem.toString());
    }

    public void createItemList() {
        for(int i=1; i<=10; i++) {
            Item item = new Item(); //setter넣기 싫다..
            item.setItemName("테스트상품 " + i);
            item.setPrice(10000 + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNm() throws Exception{
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemName("테스트 상품 1");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("QueryDsl Test")
    public void queryDsl() throws Exception{
        createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item; // 객체 생성에 new를 사용하지 않아.

        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();

        for(Item item : itemList) {
            System.out.println("아이템 목록 = " + item.toString());
        }
    }

    public void createItemList2() {
        for(int i=1; i<=5; i++) {
            Item item = new Item(); //setter넣기 싫다..
            item.setItemName("테스트상품 " + i);
            item.setPrice(10000 + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }

        for(int i=6; i<=10; i++) {
            Item item = new Item(); //setter넣기 싫다..
            item.setItemName("테스트상품 " + i);
            item.setPrice(10000 + i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 Querydsl 조회 테스트 2")
    public void queryDslTest2() {
        createItemList2();

        List<Item> allItem = itemRepository.findAll();
        for (Item item : allItem) {
            System.out.println(item.toString());
        }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;

        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(item.price.gt(price));

        //동적쿼리
        if(StringUtils.equals(itemSellStat , ItemSellStatus.SELL)) {
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0,5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder , pageable);

        System.out.println("total elements : " + itemPagingResult.getContent());
        List<Item> resultItemList = itemPagingResult.getContent();
        for (Item resultItem : resultItemList) {
            System.out.println(resultItem.toString());
        }
    }

}