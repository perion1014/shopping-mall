package com.example.shoppingmall.item.controller;

import com.example.shoppingmall.item.dto.*;
import com.example.shoppingmall.item.service.ItemService;

import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.service.QnaService;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {


    private final ItemService itemService;
    private final QnaService qnaService;

    /*유저 쇼핑몰 조회*/
    @GetMapping("")
    public String showItemList() {
        return "items/item-list";
    }

    @GetMapping("/outer")
    public String showOuterList() {
        return "items/item-list-outer";
    }

    @GetMapping("/inner")
    public String showInnerList() {
        return "items/item-list-inner";
    }

    @GetMapping("/pants")
    public String showPantsList() {
        return "items/item-list-pants";
    }

    @GetMapping("/search")
    public String showSearchResult(){return "items/item-list-result";}

    @GetMapping("/admin")
    public String getItemList(Model model) {
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

    @Value("${file.dir}")
    private String fileDir;
    @PostMapping("/admin/add")
    public String addItem(@ModelAttribute ItemAddDTO itemAddDTO,
                          @RequestParam(name="itemThumb") MultipartFile itemThumb,
                          @RequestParam("itemImg1") MultipartFile itemImg1,
                          @RequestParam("itemImg2") MultipartFile itemImg2,
                          @RequestParam("itemImg3") MultipartFile itemImg3) throws IOException{


        itemService.saveItem(itemAddDTO);
        Long itemNo = itemService.getMaxItemNo();
        itemService.saveItemPhotos(itemNo, itemAddDTO);
        itemService.saveItemStock(itemNo, itemAddDTO);


        // 파일 처리
        String myPath = "D:/intellij/workspace/";
        String fullPath = "";
        String createdDirPath = myPath + fileDir + itemNo + "/";
        Files.createDirectories(Path.of(createdDirPath));
        if (!itemThumb.isEmpty()) {
            fullPath = createdDirPath + itemThumb.getOriginalFilename();
            itemThumb.transferTo(new File(fullPath));
        }
        if (!itemImg1.isEmpty()) {
            fullPath = createdDirPath + itemImg1.getOriginalFilename();
            itemImg1.transferTo(new File(fullPath));
        }
        if (!itemImg2.isEmpty()) {
            fullPath = createdDirPath + itemImg2.getOriginalFilename();
            itemImg2.transferTo(new File(fullPath));
        }
        if (!itemImg3.isEmpty()) {
            fullPath = createdDirPath + itemImg3.getOriginalFilename();
            itemImg3.transferTo(new File(fullPath));
        }

        return "redirect:/items/admin/add";
    }


    @GetMapping("/admin/{itemNo}")
    @ResponseBody
    public String goToItemDetailPage(@PathVariable(name="itemNo") Long itemNo, Model model) throws IOException{
        ItemDTO itemDTO = itemService.findItemByNo2(itemNo);
        ItemPhotosDTO itemPhotosDTO = itemService.findItemPhotosByNo(itemNo);
        List<ItemStockDTO> itemStockDTOList = itemService.findItemStockListByNo(itemNo);
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("itemPhotosDTO", itemPhotosDTO);
        model.addAttribute("itemStockDTOList", itemStockDTOList);

        // Add the filename for the item_thumb to the model
        String itemThumbFilename = itemService.selectItemThumbByItemNo(itemNo);
        model.addAttribute("itemThumbFilename", itemThumbFilename);


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
    public  String showItemDetail(@PathVariable(name="itemNo") Long itemNo, Model model) {
        model.addAttribute("itemNo", itemNo);
        List<QnaDTO> qnaByItemNo = qnaService.getQnaByItemNo(itemNo);

        model.addAttribute("qnaByItemNo",qnaByItemNo);
        return "items/item-detail-qna-test";

    }

    @GetMapping("/itemDetailTest")
    public String goToItemDetailTestPage() {
        return "items/item-detail";
    }


}
