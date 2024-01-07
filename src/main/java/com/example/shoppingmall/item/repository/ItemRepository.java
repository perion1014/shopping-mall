package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemItemStock;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.form.ItemCategoricalSearchPageForm;
import com.example.shoppingmall.item.form.ItemCategoryPageForm;
import com.example.shoppingmall.item.form.ItemSearchForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
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

    List<Item> findItemsByName(String itemName);

    List<Item> findAllItemsBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsOuterBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsInnerBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsPantsBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    void updateItemByNo(Item item);

    void updateItemStock(ItemStock itemStock);

    void deleteItemStockByItemNo(Long itemNo);

    void deleteItemPhotosByItemNo(Long itemNo);

    void deleteItemByItemNo(Long itemNo);

    ItemPhotos findItemPhotosByItemNo(Long itemNo);

    String selectItemThumbByItemNo(Long itemNo);

    List<Item> findAllItemsByPaging(Map<String, Integer> pagingSettings);

    List<Item> getItemListPageByCategory(ItemCategoryPageForm itemCategoryPageForm);

    List<Item> getItemListPageBySearch(ItemSearchForm itemSearchForm);

    List<Item> findItemsByNameAndCategory(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    Integer getItemStockValueByItemNoAndItemSize(ItemStockDTO itemStockDTO);

    String getItemThumbByNo(Long itemNo);

    List<Item> findAllItemsByReview();
}
