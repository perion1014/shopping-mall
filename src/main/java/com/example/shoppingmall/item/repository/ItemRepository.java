package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.dto.ItemDTO;

import java.util.List;

public interface ItemRepository {
    List<ItemDTO> getAllItems();
}
