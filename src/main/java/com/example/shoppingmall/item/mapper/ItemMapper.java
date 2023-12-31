package com.example.shoppingmall.item.mapper;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemItemStock;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.form.ItemCategoricalSearchPageForm;
import com.example.shoppingmall.item.form.ItemCategoryPageForm;
import com.example.shoppingmall.item.form.ItemSearchForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void saveItem(Item item);

    Long getMaxItemNo();

    void saveItemPhotos(ItemPhotos itemPhotos);

    void saveItemStock(ItemStock itemStock);

    List<Item> findAllItems();

    List<Item> findAllItemsByCategory(String category);

    List<ItemStock> findAllItemStocks(Long itemNo);

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    Item findItemByNo(Long itemNo);

    List<Item> findItemsByName(String itemName);

    void updateItemByNo(Item item);

    List<ItemStock> findItemStocksByItemNo(Long itemNo);

    void deleteItemStockByItemNo(Long itemNo);

    void deleteItemPhotosByItemNo(Long itemNo);

    void deleteItemByItemNo(Long itemNo);

    ItemPhotos findItemPhotosByItemNo(Long itemNo);

    String selectItemThumbByItemNo(Long itemNo);

    List<Item> findAllItemsBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsOuterBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsInnerBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsPantsBySearchKeyword(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);

    List<Item> findAllItemsByPaging(Map<String, Integer> pagingSettings);

    List<Item> getItemListPageByCategory(ItemCategoryPageForm itemCategoryPageForm);

    List<Item> getItemListPageBySearch(ItemSearchForm itemSearchForm);

    List<Item> findItemListPageBySearchAndCategory(ItemCategoricalSearchPageForm itemCategoricalSearchPageForm);
}
