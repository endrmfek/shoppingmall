package com.practice.jpashoppingmall.service;

import com.practice.jpashoppingmall.dto.ItemFormDto;
import com.practice.jpashoppingmall.entity.Item;
import com.practice.jpashoppingmall.entity.ItemImage;
import com.practice.jpashoppingmall.repository.ItemImageRepository;
import com.practice.jpashoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final ItemImageService itemImageService;

    public Long saveItem(ItemFormDto itemFormDto , List<MultipartFile> itemImageFileList) throws Exception {
        Item item = itemFormDto.createItem(); // modelMapper
        itemRepository.save(item);

        for(int i=0; i<itemImageFileList.size(); i++) {
            ItemImage itemImage = new ItemImage();
            itemImage.setItem(item);
            if(i==0) {
                itemImage.setRepImageYn("Y");
            }else {
                itemImage.setRepImageYn("N");
            }
            itemImageService.saveItemImage(itemImage , itemImageFileList.get(i));
        }
        return item.getId();
    }

}
