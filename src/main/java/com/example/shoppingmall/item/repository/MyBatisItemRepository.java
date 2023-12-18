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

    private final ItemMapper itemMapper;

    @Override
    public boolean saveItem(Item item) {
        int resultCount = itemMapper.saveItem(item);
        if (resultCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Item> findAllItems() {
        List<Item> itemList = itemMapper.findAllItems();
        return itemList;
    }

    @Override
    public List<Item> findAllItemsOnsale() {
        List<Item> itemList = itemMapper.findAllItemsOnsale();
        return itemList;
    }

    @Override
    public List<Item> findAllItemsOffmarket() {
        List<Item> itemList = itemMapper.findAllItemsOffmarket();
        return itemList;
    }

    @Override
    public Item findItemByNo(Long itemNo) {
        return itemMapper.findItemByNo(itemNo);
    }

    @Override
    public List<Item> findItemsByName(Item item) {
        return itemMapper.findItemsByName(item);
    }

    @Override
    public boolean updateItemByNo(Long itemNo, Item item) {
        int resultCount = itemMapper.updateItemByNo(itemNo, item);
        if (resultCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteItemByNo(Long itemNo) {
        int resultCount = itemMapper.deleteItemByNo(itemNo);
        if (resultCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteItemByName(Item item) {
        int resultCount = itemMapper.deleteItemByName(item);
        if (resultCount == 1) {
            return true;
        } else {
            return false;
        }
    }
}
