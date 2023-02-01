package com.practice.jpashoppingmall.dto;

import com.practice.jpashoppingmall.constant.ItemSellStatus;
import com.practice.jpashoppingmall.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품을 설명해주세요.")
    private String itemDetail;

    @NotNull(message = "재고를 입력해주세요")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImageDto> itemImageDtoList = new ArrayList<>();

    private List<Long> itemImageIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public  Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }

}

