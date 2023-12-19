package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.dto.ItemStockDTO;

import java.util.List;

public interface ItemRepository {

    void saveItem(Item item);

    List<Item> findAllItems();

    List<ItemStock> joinItemByItemNo(Long itemNo);

    Item findItemByNo(Long itemNo);

    List<Item> findItemsByName(String itemName);

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    void updateItemByNo(Long itemNo, Item item);

    void deleteItemByNo(Long itemNo);

    void deleteItemByName(Item item);


}
