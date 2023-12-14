package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.dto.ItemDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBatisItemRepository implements ItemRepository{

    @Override
    public List<ItemDTO> getAllItems() {
        return null;
    }
}
