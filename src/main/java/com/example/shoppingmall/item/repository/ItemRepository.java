package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.dto.ItemDTO;

import java.util.List;

public interface ItemRepository {

    boolean saveItem(Item item);

    List<Item> findAllItems();

    Item findItemByNo(Long itemNo);

    List<Item> findItemsByName(Item item);

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    boolean updateItemByNo(Long itemNo, Item item);

    boolean deleteItemByNo(Long itemNo);

    boolean deleteItemByName(Item item);

}
