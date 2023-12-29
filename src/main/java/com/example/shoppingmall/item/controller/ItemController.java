package com.example.shoppingmall.item.controller;

import com.example.shoppingmall.item.dto.*;
import com.example.shoppingmall.item.form.ItemCategoryPageForm;
import com.example.shoppingmall.item.form.ItemSearchForm;
import com.example.shoppingmall.item.service.ItemService;

import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.service.QnaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {


    private final ItemService itemService;
    private final QnaService qnaService;

    /*유저 쇼핑몰 조회*/
    @GetMapping("")
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

    @GetMapping("/searched")
    public String showItemList2(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", true);
        session.setAttribute("ifCategorySelected", false);

        model.addAttribute("pageSettings", itemService.setItemListPage(page));
        List<ItemDTO> itemDTOList = itemService.getItemListPage(page);
        model.addAttribute("itemDTOList", itemDTOList);
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

        //List<ItemDTO> itemDTOList = itemService.findAllItemsByCategory("Outer");
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

        //List<ItemDTO> itemDTOList = itemService.findAllItemsByCategory("Inner");
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

        //List<ItemDTO> itemDTOList = itemService.findAllItemsByCategory("Pants");
        List<ItemDTO> itemDTOList = itemService.getItemListPageByCategory(page, itemCategoryPageForm);
        model.addAttribute("itemDTOList", itemDTOList);
        model.addAttribute("Category", "pants");
        return "items/item-list";
    }

    @GetMapping("/search")
    public String showItemSearched(@ModelAttribute ItemSearchForm itemSearchForm,
                                   @RequestParam(value="page", required=false, defaultValue="1") int page,
                                   @RequestParam (name = "searchKeyword", defaultValue = "") String searchKeyword,
                                   Model model,
                                   HttpServletRequest request){

        model.addAttribute("pageSettings", itemService.setItemSearchList(page, itemSearchForm));

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", true);
        session.setAttribute("ifCategorySelected", false);
        if (searchKeyword.isEmpty()) {
            return "redirect:/items/searched";
        }
        //List<ItemDTO> itemDTOList = itemService.findAllItemsBySearchKeyword(searchKeyword);
        List<ItemDTO> itemDTOList = itemService.getItemListPageBySearch(page, itemSearchForm);
        model.addAttribute("itemDTOList", itemDTOList);
        return "items/item-list";
    }

    // 구현해야 할 부분
    @GetMapping("/categoricalSearch")
    public String showItemCategoricallySearched(@ModelAttribute ItemSearchForm itemSearchForm,
                                                @RequestParam (name = "selectedCategory") String selectedCategory,
                                                HttpServletRequest request,
                                                Model model){


        HttpSession session = request.getSession();

        if (selectedCategory.equals("all")) {
            List<ItemDTO> itemDTOList = itemService.findAllItemsBySearchKeyword(itemSearchForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        } else if (selectedCategory.equals("outer")){
            List<ItemDTO> itemDTOList = itemService.findAllItemsOuterBySearchKeyword(itemSearchForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        } else if (selectedCategory.equals("inner")){
            List<ItemDTO> itemDTOList = itemService.findAllItemsInnerBySearchKeyword(itemSearchForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        } else {
            List<ItemDTO> itemDTOList = itemService.findAllItemsPantsBySearchKeyword(itemSearchForm);
            System.out.println(itemDTOList.size());
            model.addAttribute("itemDTOList", itemDTOList);
            model.addAttribute("Category", selectedCategory);
        }

        return "items/item-list";
    }

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
        /////////////////////////////////////////////////////////////////////////
        ItemDTO itemDTO = itemService.findItemByNo2(itemNo);
        ItemPhotosDTO itemPhotosDTO = itemService.findItemPhotosByNo(itemNo);
        List<ItemStockDTO> itemStockDTOList = itemService.findItemStockListByNo(itemNo);
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("itemPhotosDTO", itemPhotosDTO);
        model.addAttribute("itemStockDTOList", itemStockDTOList);

        return "items/item-detail-pym";

    }



}

