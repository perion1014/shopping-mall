package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.dto.ItemDTO;

import java.util.List;

public interface ItemRepository {

    void saveItem(Item item);

    List<Item> findAllItems();

    Item findItemByNo(Long itemNo);

    List<Item> findItemsByName(Item item);

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    void updateItemByNo(Long itemNo, Item item);

    void deleteItemByNo(Long itemNo);

    void deleteItemByName(Item item);

}
