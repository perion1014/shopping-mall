package com.example.shoppingmall.item.repository;

import com.example.shoppingmall.item.dto.ItemDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {
    List<ItemDTO> getAllItems();
}
