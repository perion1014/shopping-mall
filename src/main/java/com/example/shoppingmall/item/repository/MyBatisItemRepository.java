package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.dto.ItemStockDTO;
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
    public void saveItem(Item item) {
        itemMapper.saveItem(item);
    }

    @Override
    public void saveItemStock(ItemStock itemStock) {
        itemMapper.saveItemStock(itemStock);
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
    public List<ItemStock> joinItemByItemNo(Long itemNo) {
        List<ItemStock> itemStockList = itemMapper.joinItemByItemNo(itemNo);
        return itemStockList;
    }

    @Override
    public Item findItemByNo(Long itemNo) {
        return itemMapper.findItemByNo(itemNo);
    }

    @Override
    public List<Item> findItemsByName(String itemName) {
        return itemMapper.findItemsByName(itemName);
    }

    @Override
    public void updateItemByNo(Long itemNo, Item item) {
        itemMapper.updateItemByNo(itemNo, item);
    }

    @Override
    public void deleteItemByNo(Long itemNo) {
        itemMapper.deleteItemByNo(itemNo);
    }

    @Override
    public void deleteItemByName(Item item) {
        itemMapper.deleteItemByName(item);
    }
}
