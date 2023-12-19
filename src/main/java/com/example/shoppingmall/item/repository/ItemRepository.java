package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import java.util.List;

public interface ItemRepository {

    void saveItem(Item item);

    void saveItemStock(ItemStock itemStock);

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
