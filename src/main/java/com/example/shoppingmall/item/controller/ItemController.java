package com.example.shoppingmall.item.controller;

import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private final ItemService itemService;

    @GetMapping("/items/admin")
    public String showItemList(Model model) {
        List<ItemDTO> itemDTOList = null;
        //itemDTOList = itemService.getAllItems();
        return null;
    }

    @GetMapping("/items/admin/add")
    public String goToAddItemPage() {
        // 미구현 상태
        return null;
    }

    @PostMapping("/items/admin/add")
    public String addItem() {
        // 미구현 상태
        return null;
    }

}
