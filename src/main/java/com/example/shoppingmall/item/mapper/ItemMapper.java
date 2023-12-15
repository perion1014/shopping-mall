package com.example.shoppingmall.item.mapper;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.dto.ItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<Item> getAllItems();

    Item findByItemName(Item item);

    void saveItem(Item item);

    void deleteItemById(Item item);

    void deleteItemByName(Item item);
}
