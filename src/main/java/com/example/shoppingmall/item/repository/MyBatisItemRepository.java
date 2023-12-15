package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository{

    @Autowired
    private final ItemMapper itemMapper;

    @Override
    public List<Item> getAllItems() {
        List<Item> itemList = itemMapper.getAllItems();
        return itemList;
    }

    @Override
    public Item findByItemName(Item item) {
        return itemMapper.findByItemName(item);
    }

    @Override
    public void saveItem(Item item) {
        itemMapper.saveItem(item);
    }

    @Override
    public void deleteItemById(Item item) {
        itemMapper.deleteItemById(item);
    }

    @Override
    public void deleteItemByName(Item item) {
        itemMapper.deleteItemByName(item);
    }
}
