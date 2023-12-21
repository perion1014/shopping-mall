package com.example.shoppingmall.item.mapper;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void saveItem(Item item);

    Long getMaxItemNo();

    void saveItemPhotos(ItemPhotos itemPhotos);

    void saveItemStock(ItemStock itemStock);

    List<Item> findAllItems();

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    // item_no 칼럼을 통해 Item 테이블과 ItemStock 테이블을 join
    List<ItemStock> joinItemByItemNo(Long itemNo);

    Item findItemByNo(Long itemNo);

    List<Item> findItemsByName(String itemName);

    void updateItemByNo(Item item);

    void deleteItemByNo(Item item);

    void deleteItemByName(Item item);

    List<ItemStock> findItemStocksByItemNo(Long itemNo);

    void deleteItemPhotosByNo(ItemPhotos itemPhotos);

    void deleteItemStockByStockNo(ItemStock itemStock);
}
