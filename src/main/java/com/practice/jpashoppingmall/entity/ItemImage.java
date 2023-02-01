package com.practice.jpashoppingmall.entity;

import com.practice.jpashoppingmall.auditor.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="item_image")
@Setter
@Getter
public class ItemImage extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="item_image_id")
    private Long id;

    private String imageName; // 이미지 이름

    private String originalImageName; // 원본 이름

    private String imageUrl; // 이미지 조회 경로

    private String repImageYn; // 대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id")
    private Item item;

    public void updateItemImage(String originalImageName, String imageName, String imageUrl) {
        this.originalImageName = originalImageName;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

}
