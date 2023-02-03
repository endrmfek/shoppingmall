package com.practice.jpashoppingmall.controller;

import com.practice.jpashoppingmall.dto.ItemSearchDto;
import com.practice.jpashoppingmall.dto.MainItemDto;
import com.practice.jpashoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(ItemSearchDto itemSearchDto , Optional<Integer> page, Model model) {
        PageRequest pageable = PageRequest.of(page.orElse(0), 6);

        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);
        model.addAttribute("items" , items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage" , 5);

        return "home";
    }
}
