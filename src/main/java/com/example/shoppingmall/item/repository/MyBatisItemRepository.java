package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemItemStock;
import com.example.shoppingmall.item.domain.ItemPhotos;
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
    public Long getMaxItemNo() {
        Long maxItemNo = itemMapper.getMaxItemNo();
        return maxItemNo;
    }

    @Override
    public void saveItemPhotos(ItemPhotos itemPhotos) {
        itemMapper.saveItemPhotos(itemPhotos);
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
    public List<ItemStock> findAllItemStocks(Long itemNo) {
        List<ItemStock> itemStockList = itemMapper.findAllItemStocks(itemNo);
        return itemStockList;
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
    public List<Item> findItemsByName(String itemName) {
        return itemMapper.findItemsByName(itemName);
    }

    @Override
    public void updateItemByNo(Item item) {
        itemMapper.updateItemByNo(item);
    }

    @Override
    public void deleteItemByNo(Item item) {
        itemMapper.deleteItemByNo(item);
    }

    @Override
    public List<ItemStock> findItemStocksByItemNo(Long itemNo) {
        List<ItemStock> itemStockList = itemMapper.findItemStocksByItemNo(itemNo);
        return itemStockList;
    }

    @Override
    public void deleteItemPhotosByNo(ItemPhotos itemPhotos) {
        itemMapper.deleteItemPhotosByNo(itemPhotos);
    }

    @Override
    public void deleteItemStockByStockNo(ItemStock itemStock) {
        itemMapper.deleteItemStockByStockNo(itemStock);
    }

    @Override
    public void deleteItemStockByItemNo(Long itemNo) {
        itemMapper.deleteItemStockByItemNo(itemNo);
    }

    @Override
    public void deleteItemPhotosByItemNo(Long itemNo) {
        itemMapper.deleteItemPhotosByItemNo(itemNo);
    }

    @Override
    public void deleteItemByItemNo(Long itemNo) {
        itemMapper.deleteItemByItemNo(itemNo);
    }
}
