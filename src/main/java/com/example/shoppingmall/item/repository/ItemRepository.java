package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemItemStock;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.form.ItemCategoryPageForm;
import com.example.shoppingmall.item.form.ItemSearchForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ItemRepository {

    void saveItem(Item item);

    Long getMaxItemNo();

    void saveItemPhotos(ItemPhotos itemPhotos);

    void saveItemStock(ItemStock itemStock);

    List<Item> findAllItems();

    List<Item> findAllItemsByCategory(String category);

    List<ItemStock> findAllItemStocks(Long itemNo);

    Item findItemByNo(Long itemNo);

    List<ItemStock> findItemStocksByItemNo(Long itemNo);

    List<Item> findItemsByName(String itemName);

    List<Item> findAllItemsBySearchKeyword(ItemSearchForm itemSearchForm);

    List<Item> findAllItemsOuterBySearchKeyword(ItemSearchForm itemSearchForm);

    List<Item> findAllItemsInnerBySearchKeyword(ItemSearchForm itemSearchForm);

    List<Item> findAllItemsPantsBySearchKeyword(ItemSearchForm itemSearchForm);

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    void updateItemByNo(Item item);

    void deleteItemStockByItemNo(Long itemNo);

    void deleteItemPhotosByItemNo(Long itemNo);

    void deleteItemByItemNo(Long itemNo);

    ItemPhotos findItemPhotosByItemNo(Long itemNo);

    String selectItemThumbByItemNo(Long itemNo);


    List<Item> findAllItemsByPaging(Map<String, Integer> pagingSettings);

    List<Item> getItemListPageByCategory(ItemCategoryPageForm itemCategoryPageForm);

    List<Item> getItemListPageBySearch(ItemSearchForm itemSearchForm);
}
