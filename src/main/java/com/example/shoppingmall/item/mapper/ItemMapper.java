package com.example.shoppingmall.item.mapper;

import com.example.shoppingmall.item.domain.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    void saveItem(Item item);

    List<Item> findAllItems();

    List<Item> findAllItemsOnsale();

    List<Item> findAllItemsOffmarket();

    Item findItemByNo(Long itemNo);

    List<Item> findItemsByName(Item item);

    void updateItemByNo(Long itemNo, Item item);

    void deleteItemByNo(Long itemNo);

    void deleteItemByName(Item item);

}
