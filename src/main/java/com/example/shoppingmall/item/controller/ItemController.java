package com.example.shoppingmall.item.controller;

import com.example.shoppingmall.item.domain.ItemItemStock;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.dto.*;
import com.example.shoppingmall.item.exceptions.StorageFileNotFoundException;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.service.QnaService;
import com.example.shoppingmall.item.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

        private final ItemService itemService;

        private final QnaService qnaService;

        private final StorageService storageService;

    @GetMapping("/admin")
    public String showItemList(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAllItems();
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/item/admins-item";
    }

    @GetMapping("/admin/onsale")
    public String showItemListOnsale(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAllItemsOnsale();
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/item/admins-item";
    }

    @GetMapping("/admin/offmarket")
    public String showItemListOffmarket(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAllItemsOffmarket();
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/item/admins-item";
    }

    @PostMapping("/admin/search")
    public String searchItem(@ModelAttribute ItemSearchDTO itemSearchDTO, Model model) {
        List<ItemDTO> itemDTOList = itemService.findItemsByName(itemSearchDTO);
        model.addAttribute("itemDTOList", itemDTOList);
        return "admins/item/admins-item";
    }

    @GetMapping("/admin/add")
    public String goToAddItemPage() {
        return "admins/item/admins-item-add";
    }

    @PostMapping("/admin/add")
    public String addItem(@ModelAttribute ItemAddDTO itemAddDTO,
                          @RequestParam(name="itemThumb") MultipartFile itemThumb){
        itemService.saveItem(itemAddDTO);
        Long itemNo = itemService.getMaxItemNo();
        itemService.saveItemPhotos(itemNo, itemAddDTO);
        itemService.saveItemStock(itemNo, itemAddDTO);

        // 파일 처리
        storageService.store(itemThumb);

        return "redirect:/items/admin/add";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/admin/{itemNo}")
    public String goToItemDetailPage(@PathVariable(name="itemNo") Long itemNo, Model model) throws IOException{
        ItemDTO itemDTO = itemService.findItemByNo2(itemNo);
        ItemPhotosDTO itemPhotosDTO = itemService.findItemPhotosByNo(itemNo);
        List<ItemStockDTO> itemStockDTOList = itemService.findItemStockListByNo(itemNo);
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("itemPhotosDTO", itemPhotosDTO);
        model.addAttribute("itemStockDTOList", itemStockDTOList);

        Resource file = storageService.loadAsResource(itemPhotosDTO.getItemThumb());
        model.addAttribute("file", file);
        if (file == null) {
            //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
        return "admins/item/admins-item-detail";
    }

    @PostMapping("/admin/{itemNo}/update")
    public String updateItem(@PathVariable(name="itemNo") Long itemNo, @ModelAttribute ItemUpdateDTO itemUpdateDTO) {
        itemService.updateItemByNo(itemNo, itemUpdateDTO);
        return "redirect:/items/admin/{itemNo}";

    }

    @GetMapping("/admin/{itemNo}/delete")
    public String deleteItem(@PathVariable(name="itemNo") Long itemNo) {
        itemService.deleteItemStockByItemNo(itemNo);
        itemService.deleteItemPhotosByItemNo(itemNo);
        itemService.deleteItemPyItemNo(itemNo);
        return "redirect:/items/admin";
    }

    // kch QnA test
    @GetMapping("/{itemNo}")
    public  String showItemDetail(@PathVariable(name = "itemNo") Long itemNo, Model model) {
        model.addAttribute("itemNo", itemNo);
        List<QnaDTO> qnaByItemNo = qnaService.getQnaByItemNo(itemNo);

        model.addAttribute("qnaByItemNo",qnaByItemNo);
        return "items/item-detail-qna-test";
    }

}
