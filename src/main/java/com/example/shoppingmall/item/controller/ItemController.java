package com.example.shoppingmall.item.controller;

import com.example.shoppingmall.item.dto.*;
import com.example.shoppingmall.item.form.ItemCategoricalSearchPageForm;
import com.example.shoppingmall.item.form.ItemCategoryPageForm;
import com.example.shoppingmall.item.form.ItemSearchForm;
import com.example.shoppingmall.item.service.ItemService;

import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.service.QnaService;

import com.example.shoppingmall.review.dto.ReviewDTO;
import com.example.shoppingmall.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {


    private final ItemService itemService;
    private final QnaService qnaService;
    private final ReviewService reviewService;

    /*유저 쇼핑몰 조회*/
    @GetMapping("/all") /* state: not searched */
    public String showItemList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                               Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", false);
        session.setAttribute("ifCategorySelected", true);

        model.addAttribute("pageSettings", itemService.setItemListPage(page));
        List<ItemDTO> itemDTOList = itemService.getItemListPage(page);
        model.addAttribute("itemDTOList", itemDTOList);
        model.addAttribute("Category", "all");
        return "items/item-list";
    }

    @GetMapping("/searched")    /* state: searched */
    public String showItemList2(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", true);
        session.setAttribute("ifCategorySelected", false);

        model.addAttribute("pageSettings", itemService.setItemListPage(page));
        List<ItemDTO> itemDTOList = itemService.getItemListPage(page);
        model.addAttribute("itemDTOList", itemDTOList);
        model.addAttribute("Category", "all");
        return "items/item-list";
    }

    @GetMapping("/outer")
    public String showOuterList(@ModelAttribute ItemCategoryPageForm itemCategoryPageForm,
                                @RequestParam(value="page", required=false, defaultValue="1") int page,
                                Model model, HttpServletRequest request) {

        itemCategoryPageForm.setCategory("outer");
        model.addAttribute("pageSettings", itemService.setItemListPageByCategory(page, itemCategoryPageForm));

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", false);
        session.setAttribute("ifCategorySelected", true);

        List<ItemDTO> itemDTOList = itemService.getItemListPageByCategory(page, itemCategoryPageForm);
        model.addAttribute("itemDTOList", itemDTOList);
        model.addAttribute("Category", "outer");
        return "items/item-list";
    }

    @GetMapping("/inner")
    public String showInnerList(@ModelAttribute ItemCategoryPageForm itemCategoryPageForm,
                                @RequestParam(value="page", required=false, defaultValue="1") int page,
                                Model model, HttpServletRequest request) {

        itemCategoryPageForm.setCategory("inner");
        model.addAttribute("pageSettings", itemService.setItemListPageByCategory(page, itemCategoryPageForm));

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", false);
        session.setAttribute("ifCategorySelected", true);

        List<ItemDTO> itemDTOList = itemService.getItemListPageByCategory(page, itemCategoryPageForm);
        model.addAttribute("itemDTOList", itemDTOList);
        model.addAttribute("Category", "inner");
        return "items/item-list";
    }

    @GetMapping("/pants")
    public String showPantsList(@ModelAttribute ItemCategoryPageForm itemCategoryPageForm,
                                @RequestParam(value="page", required=false, defaultValue="1") int page,
                                Model model, HttpServletRequest request) {

        itemCategoryPageForm.setCategory("pants");
        model.addAttribute("pageSettings", itemService.setItemListPageByCategory(page, itemCategoryPageForm));

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", false);
        session.setAttribute("ifCategorySelected", true);

        List<ItemDTO> itemDTOList = itemService.getItemListPageByCategory(page, itemCategoryPageForm);
        model.addAttribute("itemDTOList", itemDTOList);
        model.addAttribute("Category", "pants");
        return "items/item-list";
    }

    @GetMapping("/search")
    public String showItemSearched(@ModelAttribute ItemSearchForm itemSearchForm,
                                   @RequestParam(value="page", required=false, defaultValue="1") int page,
                                   @RequestParam (name = "searchKeyword", required = false) String searchKeyword,
                                   Model model,
                                   HttpServletRequest request){

        itemSearchForm.setSearchKeyword(searchKeyword);
        model.addAttribute("pageSettings", itemService.setItemSearchList(page, itemSearchForm));

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", true);
        session.setAttribute("ifCategorySelected", false);
        if (searchKeyword.isEmpty()) {
            return "redirect:/items/searched";
        }
        model.addAttribute("searchKeyword", searchKeyword);
        List<ItemDTO> itemDTOList = itemService.getItemListPageBySearch(page, itemSearchForm);
        model.addAttribute("itemDTOList", itemDTOList);
        return "items/item-list";
    }

    // 구현해야 할 부분
    @GetMapping("/categoricalSearch")
    public String showItemCategoricallySearched(@ModelAttribute ItemCategoricalSearchPageForm itemCategoricalSearchPageForm,
                                                @RequestParam(value="page", required=false, defaultValue="1") int page,
                                                @RequestParam (name = "selectedCategory") String selectedCategory,
                                                Model model){

        if (selectedCategory.equals("all")) {
            model.addAttribute("pageSettings", itemService.setItemSearchListByAll(page, itemCategoricalSearchPageForm));
            List<ItemDTO> itemDTOList = itemService.findAllItemsBySearchKeyword(page, itemCategoricalSearchPageForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        } else if (selectedCategory.equals("outer")){
            itemCategoricalSearchPageForm.setCategory(selectedCategory);
            model.addAttribute("pageSettings", itemService.setItemSearchListByCategory(page, itemCategoricalSearchPageForm));
            List<ItemDTO> itemDTOList = itemService.findAllItemsOuterBySearchKeyword(page, itemCategoricalSearchPageForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        } else if (selectedCategory.equals("inner")){
            itemCategoricalSearchPageForm.setCategory(selectedCategory);
            model.addAttribute("pageSettings", itemService.setItemSearchListByCategory(page, itemCategoricalSearchPageForm));
            List<ItemDTO> itemDTOList = itemService.findAllItemsInnerBySearchKeyword(page, itemCategoricalSearchPageForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        } else {
            itemCategoricalSearchPageForm.setCategory(selectedCategory);
            model.addAttribute("pageSettings", itemService.setItemSearchListByCategory(page, itemCategoricalSearchPageForm));
            List<ItemDTO> itemDTOList = itemService.findAllItemsPantsBySearchKeyword(page, itemCategoricalSearchPageForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        }

        return "items/item-list";
    }

    @GetMapping("/admin")
    public String getItemList(@ModelAttribute ItemSearchForm itemSearchForm,
                              @RequestParam(value="page", required=false, defaultValue="1") int page,
                              Model model) {

        if (itemSearchForm.getSearchKeyword() == null || itemSearchForm.getSearchKeyword().isEmpty()) {
            model.addAttribute("pageSettings", itemService.setItemListPage(page));
            List<ItemDTO> itemDTOList = itemService.getItemListPage(page);
            model.addAttribute("itemDTOList", itemDTOList);
        } else {
            model.addAttribute("pageSettings", itemService.setItemSearchList(page, itemSearchForm));
            List<ItemDTO> itemDTOList = itemService.getItemListPageBySearch(page, itemSearchForm);
            model.addAttribute("itemDTOList", itemDTOList);
        }
        return "admins/item/admins-item";
    }

    @GetMapping("/admin/onsale")
    public String showItemListOnsale(Model model) {
        List<ItemDTO> itemDTOList = itemService.findAllItemsOnsale();
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
                          @RequestParam("itemThumb") MultipartFile itemThumb,
                          @RequestParam("itemImg1") MultipartFile itemImg1,
                          @RequestParam("itemImg2") MultipartFile itemImg2,
                          @RequestParam("itemImg3") MultipartFile itemImg3) throws IOException{
        itemService.saveItem(itemAddDTO);
        Long itemNo = itemService.getMaxItemNo();
        itemService.saveItemPhotos(itemNo, itemAddDTO, itemThumb, itemImg1, itemImg2, itemImg3);
        itemService.saveItemStock(itemNo, itemAddDTO);
        return "redirect:/items/admin/add";
    }


    @GetMapping("/admin/{itemNo}")
    public String goToItemDetailPage(@PathVariable(name="itemNo") Long itemNo, Model model){
        ItemDTO itemDTO = itemService.findItemByNo2(itemNo);
        ItemPhotosDTO itemPhotosDTO = itemService.findItemPhotosByNo(itemNo);
        List<ItemStockDTO> itemStockDTOList = itemService.findItemStockListByNo(itemNo);
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("itemPhotosDTO", itemPhotosDTO);
        model.addAttribute("itemStockDTOList", itemStockDTOList);

        return "admins/item/admins-item-detail";
    }

    @PostMapping("/admin/{itemNo}/update")
    public String updateItem(@PathVariable(name="itemNo") Long itemNo,
                             @ModelAttribute ItemUpdateDTO itemUpdateDTO,
                             @RequestParam(name = "itemStockNo1") Long itemStockNo1,
                             @RequestParam(name = "itemStockNo2") Long itemStockNo2,
                             @RequestParam(name = "itemStockNo3") Long itemStockNo3,
                             @RequestParam(name = "itemStockValue1") Integer itemStockValue1,
                             @RequestParam(name = "itemStockValue2") Integer itemStockValue2,
                             @RequestParam(name = "itemStockValue3") Integer itemStockValue3
                             ) {
        List<ItemStockDTO> itemStockDTOList = new ArrayList<>();
        ItemStockDTO itemStockDTO1 = new ItemStockDTO();
        itemStockDTO1.setItemStockNo(itemStockNo1);
        itemStockDTO1.setItemNo(itemNo);
        itemStockDTO1.setItemSize("S");
        itemStockDTO1.setItemStockValue(itemStockValue1);
        itemStockDTOList.add(itemStockDTO1);

        ItemStockDTO itemStockDTO2 = new ItemStockDTO();
        itemStockDTO2.setItemStockNo(itemStockNo2);
        itemStockDTO2.setItemNo(itemNo);
        itemStockDTO2.setItemSize("M");
        itemStockDTO2.setItemStockValue(itemStockValue2);
        itemStockDTOList.add(itemStockDTO2);

        ItemStockDTO itemStockDTO3 = new ItemStockDTO();
        itemStockDTO3.setItemStockNo(itemStockNo3);
        itemStockDTO3.setItemNo(itemNo);
        itemStockDTO3.setItemSize("L");
        itemStockDTO3.setItemStockValue(itemStockValue3);
        itemStockDTOList.add(itemStockDTO3);

        itemUpdateDTO.setItemStockDTOList(itemStockDTOList);

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


    @GetMapping("/{itemNo}")
    public  String showItemDetail(@PathVariable(name="itemNo") Long itemNo, Model model) {
        model.addAttribute("itemNo", itemNo);
        List<QnaDTO> qnaByItemNo = qnaService.getQnaByItemNo(itemNo);
        model.addAttribute("qnaByItemNo",qnaByItemNo);

        List<ReviewDTO> reviewByItemNo = reviewService.getReviewListByitemNo(itemNo);
        model.addAttribute("reviewByItemNo",reviewByItemNo);
        /////////////////////////////////////////////////////////////////////////
        ItemDTO itemDTO = itemService.findItemByNo2(itemNo);
        ItemPhotosDTO itemPhotosDTO = itemService.findItemPhotosByNo(itemNo);
        List<ItemStockDTO> itemStockDTOList = itemService.findItemStockListByNo(itemNo);
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("itemPhotosDTO", itemPhotosDTO);
        model.addAttribute("itemStockDTOList", itemStockDTOList);

        return "items/item-detail";

    }



}

