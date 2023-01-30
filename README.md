# 간단한 쇼핑몰 만들어보기

***

1. 엔티티 만들기
   * **Item table**
     * item_id | bigint | not null
     * item_detail | longtext | not null
     * item_name | varchar(50) | not null
     * item_sell_status | varchar(255)
     * price integer | not null
     * reg_time | datetime(6)
     * stock_number | integer not null
     * update_time | datetime(6)
     * primary key (item_id)
     
2. Repository 설계 -> **jpa 사용**
   
     