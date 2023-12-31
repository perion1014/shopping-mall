package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.*;
import com.example.shoppingmall.item.form.ItemCategoricalSearchPageForm;
import com.example.shoppingmall.item.form.ItemCategoryPageForm;
import com.example.shoppingmall.item.form.ItemPageForm;
import com.example.shoppingmall.item.form.ItemSearchForm;
import com.example.shoppingmall.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Value("${file.dir}")
    private String fileDir;

    public void saveItem(ItemAddDTO itemAddDTO) {
        Item item = ItemAddDTO.itemAddDTOToItem(itemAddDTO);
        itemRepository.saveItem(item);
    }

    public Long getMaxItemNo() {
        Long maxItemNo = itemRepository.getMaxItemNo();
        return maxItemNo;
    }

    public void saveItemPhotos(Long itemNo, ItemAddDTO itemAddDTO, MultipartFile itemThumb, MultipartFile itemImg1, MultipartFile itemImg2, MultipartFile itemImg3) throws IOException{
        ItemPhotos itemPhotos = ItemAddDTO.itemAddDTOToItemPhotos(itemNo, itemAddDTO);
        itemRepository.saveItemPhotos(itemPhotos);
        // 파일 처리 (추가)
        String myPath = "D:/intellij/workspace/";
        //String myPath = "C:/Users/ckd39/Downloads/";
        String fullPath = "";
        //D:/intellij/workspace/shopping-mall/src/main/resources/static/images/itemImages/
        String createdDirPath = myPath + fileDir + itemNo + "/";
        Files.createDirectories(Path.of(createdDirPath));
        Files.createDirectories(Path.of(createdDirPath + "thumb/"));
        if (!itemThumb.isEmpty()) {
            fullPath = createdDirPath + "thumb/" + itemThumb.getOriginalFilename();
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
    }

    public void saveItemStock(Long itemNo, ItemAddDTO itemAddDTO) {
        List<ItemStock> itemStockList = ItemAddDTO.itemAddToItemStockList(itemNo, itemAddDTO);
        for (ItemStock itemStock: itemStockList) {
            itemRepository.saveItemStock(itemStock);
        }
    }

    public List<ItemDTO> findAllItems() {
        List<Item> itemList = itemRepository.findAllItems();
        return convertToItemDTOList(itemList);
    }

    public List<ItemDTO> findAllItemsOnsale() {
        List<Item> itemList = itemRepository.findAllItemsOnsale();
        return convertToItemDTOList(itemList);
    }

    public List<ItemDTO> findItemsByName(ItemSearchDTO itemSearchDTO) {
        List<Item> itemList = itemRepository.findItemsByName(itemSearchDTO.getItemName());
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.itemToItemDTO(item));
        }
        return itemDTOList;
    }

    public List<ItemDTO> findAllItemsBySearchKeyword(int page, ItemCategoricalSearchPageForm itemCategoricalSearchPageForm) {

        int itemsPerPage = 12;
        int startPage = (page - 1) * itemsPerPage;

        itemCategoricalSearchPageForm.setStartPage(startPage);
        itemCategoricalSearchPageForm.setItemsPerPage(itemsPerPage);
        List<Item> itemList = itemRepository.findAllItemsBySearchKeyword(itemCategoricalSearchPageForm);

        return convertToItemDTOList(itemList);
    }



    public List<ItemDTO> findAllItemsOuterBySearchKeyword(int page, ItemCategoricalSearchPageForm itemCategoricalSearchPageForm) {

        int itemsPerPage = 12;
        int startPage = (page - 1) * itemsPerPage;

        itemCategoricalSearchPageForm.setStartPage(startPage);
        itemCategoricalSearchPageForm.setItemsPerPage(itemsPerPage);

        List<Item> itemList = itemRepository.findAllItemsOuterBySearchKeyword(itemCategoricalSearchPageForm);
        return convertToItemDTOList(itemList);
    }

    public List<ItemDTO> findAllItemsInnerBySearchKeyword(int page, ItemCategoricalSearchPageForm itemCategoricalSearchPageForm) {

        int itemsPerPage = 12;
        int startPage = (page - 1) * itemsPerPage;

        itemCategoricalSearchPageForm.setStartPage(startPage);
        itemCategoricalSearchPageForm.setItemsPerPage(itemsPerPage);

        List<Item> itemList = itemRepository.findAllItemsInnerBySearchKeyword(itemCategoricalSearchPageForm);
        return convertToItemDTOList(itemList);
    }

    public List<ItemDTO> findAllItemsPantsBySearchKeyword(int page, ItemCategoricalSearchPageForm itemCategoricalSearchPageForm) {

        int itemsPerPage = 12;
        int startPage = (page - 1) * itemsPerPage;

        itemCategoricalSearchPageForm.setStartPage(startPage);
        itemCategoricalSearchPageForm.setItemsPerPage(itemsPerPage);

        List<Item> itemList = itemRepository.findAllItemsPantsBySearchKeyword(itemCategoricalSearchPageForm);
        return convertToItemDTOList(itemList);
    }



    public void updateItemByNo(Long itemNo, ItemUpdateDTO itemUpdateDTO) {
        Item item = ItemUpdateDTO.itemUpdateDTOToItem(itemNo, itemUpdateDTO);
        itemRepository.updateItemByNo(item);
        for (ItemStock itemStock: item.getItemStockList()) {
            itemRepository.updateItemStock(itemStock);
        }
    }

    public void deleteItemStockByItemNo(Long itemNo) {
        itemRepository.deleteItemStockByItemNo(itemNo);
    }

    public void deleteItemPhotosByItemNo(Long itemNo) {
        itemRepository.deleteItemPhotosByItemNo(itemNo);
        // 파일 처리 (삭제)
        String myPath = "D:/intellij/workspace/";
        //String myPath = "C:/Users/ckd39/Downloads/";
        String createdDirPath = myPath + fileDir + itemNo + "/";
        //D:/intellij/workspace/shopping-mall/src/main/resources/static/images/itemImages/
        try {
            Files.walk(Path.of(createdDirPath))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteItemPyItemNo(Long itemNo) {
        itemRepository.deleteItemByItemNo(itemNo);
    }

    public ItemDTO findItemByNo2(Long itemNo) {
        Item item =  itemRepository.findItemByNo(itemNo);
        return ItemDTO.itemToItemDTO2(item);
    }

    public ItemPhotosDTO findItemPhotosByNo(Long itemNo) {
        ItemPhotos itemPhotos = itemRepository.findItemPhotosByItemNo(itemNo);
        ItemPhotosDTO itemPhotosDTO = ItemPhotosDTO.toItemPhotosDTO(itemPhotos);
        return itemPhotosDTO;
    }

    public List<ItemStockDTO> findItemStockListByNo(Long itemNo) {
        List<ItemStock> itemStockList = itemRepository.findAllItemStocks(itemNo);
        return ItemStockDTO.toItemStockDTOList(itemStockList);
    }

    @Transactional(readOnly = true)
    public ItemPageForm setItemListPage(int page) { // setter

        int itemsPerPage = 12;
        int pageLimit = 5;

        int itemCount = itemRepository.findAllItems().size();

        int totalPage = (int) (Math.ceil((double) itemCount / itemsPerPage));
        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ItemPageForm(page, totalPage, startPage, endPage);
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> getItemListPage(int page) {

        int itemsPerPage = 12;
        int startPage = (page - 1) * itemsPerPage;

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("itemsPerPage", itemsPerPage);

        List<Item> itemList = itemRepository.findAllItemsByPaging(pagingSettings);
        return convertToItemDTOList(itemList);
    }


    @Transactional(readOnly = true)
    public ItemPageForm setItemListPageByCategory(int page, ItemCategoryPageForm itemCategoryPageForm) { // setter

        int itemsPerPage = 12;
        int pageLimit = 5;

        int itemCount = itemRepository.findAllItemsByCategory(itemCategoryPageForm.getCategory()).size();

        int totalPage = (int) (Math.ceil((double) itemCount / itemsPerPage));
        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ItemPageForm(page, totalPage, startPage, endPage);
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> getItemListPageByCategory(int page, ItemCategoryPageForm itemCategoryPageForm) {

        int itemsPerPage = 12;
        int startPage = (page - 1) * itemsPerPage;

        itemCategoryPageForm.setStartPage(startPage);
        itemCategoryPageForm.setItemsPerPage(itemsPerPage);

        List<Item> itemList = itemRepository.getItemListPageByCategory(itemCategoryPageForm);

        return convertToItemDTOList(itemList);
    }

    @Transactional(readOnly = true)
    public ItemPageForm setItemSearchList(int page, ItemSearchForm itemSearchForm) {

        int itemsPerPage = 12;
        int pageLimit = 5;

        int itemCount = itemRepository.findItemsByName(itemSearchForm.getSearchKeyword()).size();

        int totalPage = (int) (Math.ceil((double) itemCount / itemsPerPage));
        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        return new ItemPageForm(page, totalPage, startPage, endPage);
    }


    @Transactional(readOnly = true)
    public List<ItemDTO> getItemListPageBySearch(int page, ItemSearchForm itemSearchForm) {

        int itemsPerPage = 12;
        int startPage = (page - 1) * itemsPerPage;

        itemSearchForm.setStartPage(startPage);
        itemSearchForm.setItemsPerPage(itemsPerPage);

        List<Item> itemList = itemRepository.getItemListPageBySearch(itemSearchForm);

        return convertToItemDTOList(itemList);
    }

    @Transactional(readOnly = true)
    public ItemPageForm setItemSearchListByAll(int page, ItemCategoricalSearchPageForm itemCategoricalSearchPageForm) {
        int itemsPerPage = 12;
        int pageLimit = 5;

        int itemCount = itemRepository.findItemsByName(itemCategoricalSearchPageForm.getSearchKeyword()).size();

        int totalPage = (int) (Math.ceil((double) itemCount / itemsPerPage));
        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        return new ItemPageForm(page, totalPage, startPage, endPage);
    }

    @Transactional(readOnly = true)
    public ItemPageForm setItemSearchListByCategory(int page, ItemCategoricalSearchPageForm itemCategoricalSearchPageForm) {

        int itemsPerPage = 12;
        int pageLimit = 5;

        int itemCount = itemRepository.findItemsByNameAndCategory(itemCategoricalSearchPageForm).size();

        int totalPage = (int) (Math.ceil((double) itemCount / itemsPerPage));
        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        return new ItemPageForm(page, totalPage, startPage, endPage);
    }

    public Integer getItemStockValueByItemNoAndItemSize(ItemStockDTO itemStockDTO) {
        Integer itemStockValue = itemRepository.getItemStockValueByItemNoAndItemSize(itemStockDTO);
        return itemStockValue;
    }

    public String getItemThumbByNo(Long itemNo) {
        return itemRepository.getItemThumbByNo(itemNo);
    }

    public List<ItemDTO> findAllItemsByReview() {
        List<Item> itemList = itemRepository.findAllItemsByReview();
        return convertToItemDTOList(itemList);
    }

    public void reduceItemStocks(ItemStockReduceDTO itemStockReduceDTO) {
        itemRepository.reduceItemStocks(itemStockReduceDTO);
    }

    public List<ItemDTO> convertToItemDTOList(List<Item> itemList) {
        for (Item item: itemList) {
            Long itemNo = item.getItemNo();
            item.setItemPhotos(itemRepository.findItemPhotosByItemNo(itemNo));
            List<ItemStock> itemStockList = itemRepository.findAllItemStocks(itemNo);
            item.setItemStockList(itemStockList);
        }
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            item.setItemPhotosDTO(ItemPhotosDTO.toItemPhotosDTO(item.getItemPhotos()));
            List<ItemStockDTO> itemStockDTOList = ItemStockDTO.toItemStockDTOList(item.getItemStockList());
            item.setItemStockDTOList(itemStockDTOList);
            itemDTOList.add(ItemDTO.itemToItemDTO(item));
        }
        return itemDTOList;
    }
}



