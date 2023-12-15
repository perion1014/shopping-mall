package com.example.shoppingmall.item.controller;

import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private final ItemService itemService;

    @GetMapping("/admin")
    public String showItemList(Model model) {
        List<ItemDTO> itemDTOList = itemService.getAllItems();
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/admin-item";
    }

    @PostMapping("/admin/search")
    public String searchItem(@ModelAttribute ItemDTO itemDTO) {
        return null;
    }


    @GetMapping("/admin/add")
    public String goToAddItemPage() {
        return "admins/admin-item-add";
    }

    @PostMapping("/admin/add")
    public String addItem(@ModelAttribute ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
        return "redirect:/items/admin";
    }

    @GetMapping("/admin/{itemNo}")
    public String goToItemDetailPage() {
        return null;
    }

    @PostMapping("/admin/{itemNo}/update")
    public String updateItem(@ModelAttribute ItemDTO itemDTO) {
        //itemService.updateItem(itemDTO);
        return "redirect:/items/admin";
    }

    @PostMapping("/admin/{itemNo}/delete")
    public String deleteItem(@ModelAttribute ItemDTO itemDTO) {
        if (itemDTO.getItemNo() != null) {
            itemService.deleteItemById(itemDTO);
        } else if (itemDTO.getItemName() != null) {
            itemService.deleteItemByName(itemDTO);
        }
        return "redirect:/items/admin";
    }

}
