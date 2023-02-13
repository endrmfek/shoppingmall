package com.practice.jpashoppingmall.controller;

import com.practice.jpashoppingmall.dto.ItemFormDto;
import com.practice.jpashoppingmall.dto.ItemSearchDto;
import com.practice.jpashoppingmall.entity.Item;
import com.practice.jpashoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
     * 상품 등록 페이지 진입
     * */
    @GetMapping("/admin/item/new")
    public String itemForm(Model model) { //ItemFormDto 를 매개변수에 그냥 넣어도됨.
        model.addAttribute("itemFormDto" , new ItemFormDto());
        return "/item/itemForm";
    }

    /**
     * 상품 등록 (Create)
     * */
    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto,
                          BindingResult bindingResult,
                          Model model,
                          @RequestParam("itemImageFile") List<MultipartFile> itemImageFileList) {

        if(bindingResult.hasErrors()) {
            return "item/itemForm";
        }

        if(itemImageFileList.get(0).isEmpty() && itemFormDto.getId()== null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            itemService.saveItem(itemFormDto, itemImageFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    /**
     * 상품 수정 화면 진입 -> itemId로 구분
     * */
    @GetMapping("/admin/item/{itemId}")
    public String itemUpdate(@PathVariable("itemId") Long itemId,
                             Model model) {
        try{
            //DB에서 정보 가져와서
            ItemFormDto itemFormDto = itemService.getItem(itemId);
            //Model로 넘겨
            model.addAttribute("itemFormDto", itemFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDto" , new ItemFormDto());
            return "item/itemForm";
        }

        return "item/itemForm";
    }

    /**
     * 상품 상세 페이지 (Read)
     * */
    @GetMapping("/item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId , Model model) {
        ItemFormDto itemFormDto = itemService.getItem(itemId);
        model.addAttribute("item", itemFormDto);
        return "item/itemDetail";
    }

    /**
     * 상품 수정 (Update)
     * */
    @PostMapping("/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto,
                             BindingResult bindingResult,
                             @RequestParam("itemImageFile") List<MultipartFile> itemImageFileList,
                             Model model) {
        if(bindingResult.hasErrors()) {
            return "item/itemForm";
        }

        if(itemImageFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력값입니다.");
            return "item/itemForm";
        }

        try {
            itemService.updateItem(itemFormDto, itemImageFileList);
        }catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }


    @GetMapping({"/admin/items" , "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto,
                             @PathVariable("page") Optional<Integer> page,
                             Model model) {

        Pageable pageable = PageRequest.of(page.orElse(0), 3);

        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto" , itemSearchDto);
        model.addAttribute("maxPage" , 5);
        return "item/itemManage";
    }


}
