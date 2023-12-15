package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.dto.ItemDTO;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();

    Item findByItemName(Item item);

    void saveItem(Item item);

    void deleteItemById(Item item);

    void deleteItemByName(Item item);
}
