package com.practice.jpashoppingmall.service;

import com.practice.jpashoppingmall.dto.ItemFormDto;
import com.practice.jpashoppingmall.dto.ItemImageDto;
import com.practice.jpashoppingmall.dto.ItemSearchDto;
import com.practice.jpashoppingmall.dto.MainItemDto;
import com.practice.jpashoppingmall.entity.Item;
import com.practice.jpashoppingmall.entity.ItemImage;
import com.practice.jpashoppingmall.repository.ItemImageRepository;
import com.practice.jpashoppingmall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.TransactionScoped;
import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public ItemFormDto getItem(Long itemId) {
        List<ItemImage> itemImageList = itemImageRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImageDto> itemImageDtoList = new ArrayList<>();

        //엔티티 -> Dto 변환
        for (ItemImage itemImage : itemImageList) {
            ItemImageDto itemImageDto = ItemImageDto.of(itemImage);
            itemImageDtoList.add(itemImageDto);
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImageDtoList(itemImageDtoList);
        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto , List<MultipartFile> itemImageFileList) throws Exception {
        Item item = itemRepository.findById(itemFormDto.getId())
                                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        List<Long> itemImageIds = itemFormDto.getItemImageIds();

        for(int i=0; i<itemImageIds.size(); i++) {
            itemImageService.updateItemImage(itemImageIds.get(i) , itemImageFileList.get(i));
        }
        return item.getId();
    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto , Pageable pageable) {
        return itemRepository.getAdminItemPage(itemSearchDto , pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        return itemRepository.getMainItemPage(itemSearchDto , pageable);
    }

}
