# 간단한 쇼핑몰 만들어보기

***

1. 엔티티 
   * **상품 테이블 (item)**
     * item_id | bigint | not null
     * item_detail | longtext | not null
     * item_name | varchar(50) | not null
     * item_sell_status | varchar(255)
     * price integer | not null
     * reg_time | datetime(6)
     * stock_number | integer not null
     * update_time | datetime(6)
     * primary key (item_id)
   * **회원 테이블 (member)**
     * member_id | bigint | not null
     * name | VARCHAR(255) 
     * email | VARCHAR(255)
     * password | VARCHAR(255)
     * address | VARCHAR(255)
     * role | VARCHAR(255)
     * primary key (member_id)
2. Repository 설계 -> **jpa , QueryDLS**
3. Spring security 설정
   * **Deprecated** 
     * WebSecurityConfigurerAdapter
       * 공식문서 : https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
       * 참조 블로그 : https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
     
   
     