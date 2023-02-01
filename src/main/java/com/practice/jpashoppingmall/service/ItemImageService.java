package com.practice.jpashoppingmall.service;

import com.practice.jpashoppingmall.entity.ItemImage;
import com.practice.jpashoppingmall.repository.ItemImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImageService {

    private final ItemImageRepository itemImageRepository;
    private final FileService fileService;

    @Value("${itemImageLocation}")
    private String itemImageLocation;

    public void saveItemImage(ItemImage itemImage, MultipartFile itemImageFile) throws Exception {
        String originalImageName = itemImageFile.getOriginalFilename();
        String imageName = "";
        String imageUrl = "";

        if(!StringUtils.isEmpty(originalImageName)) {
            imageName = fileService.uploadFile(itemImageLocation , originalImageName , itemImageFile.getBytes());
            imageUrl = "/images/item" + imageName;
        }

        itemImage.updateItemImage(originalImageName , imageName, imageUrl);
        itemImageRepository.save(itemImage);
    }

}
