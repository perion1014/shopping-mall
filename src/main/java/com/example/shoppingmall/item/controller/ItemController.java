package com.example.shoppingmall.item.controller;

import com.example.shoppingmall.item.dto.ItemAddDTO;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.dto.ItemSearchDTO;
import com.example.shoppingmall.item.dto.ItemUpdateDTO;
import com.example.shoppingmall.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;  // 의존성 주입; @RequiredArgsConstructor 필요

    @GetMapping("/admin")
    public String showItemList(Model model) {
        List<ItemAddDTO> itemAddDTOList = itemService.findAllItems();
        model.addAttribute("itemDTOList", itemAddDTOList);
        return "admins/admin-item";
    }

    @GetMapping("/admin/onsale")
    public String showItemListOnsale(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAllItemsOnsale();
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/admin-item";
    }

    @GetMapping("/admin/offmarket")
    public String showItemListOffmarket(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAllItemsOffmarket();
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/admin-item";
    }

    @PostMapping("/admin/search")
    public String searchItem(@ModelAttribute ItemSearchDTO itemSearchDTO, Model model) {
        List<ItemDTO> itemDTOList = itemService.findItemsByName(itemSearchDTO);
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/admin-item";
    }

    @GetMapping("/admin/add")
    public String goToAddItemPage() {
        return "admins/admin-item-add";
    }

    @PostMapping("/admin/add")
    public String addItem(@ModelAttribute ItemAddDTO itemAddDTO) {
        itemService.saveItem(itemAddDTO);
        return "redirect:/items/admin";
    }

    @GetMapping("/admin/{itemNo}")
    public String goToItemDetailPage(@PathVariable Long itemNo, Model model) {
        ItemDTO itemDTO = itemService.findItemByNo(itemNo);
        model.addAttribute("itemDTO", itemDTO);
        return null;    // 수정 예정: html 파일 생성 후 해당 html 파일을 넣을 것임.
    }

    @PostMapping("/admin/{itemNo}/update")
    public String updateItem(@PathVariable Long itemNo, @ModelAttribute ItemUpdateDTO itemUpdateDTO) {
        itemService.updateItemByNo(itemNo, itemUpdateDTO);
        // System.out.println("Succeeded to update item; redirect to '/items/admin'");
        return "redirect:/items/admin";

    }

    @PostMapping("/admin/{itemNo}/delete")
    public String deleteItem(@PathVariable Long itemNo) {
        itemService.deleteItemByNo(itemNo);
        return "redirect:/items/admin";
    }

}
