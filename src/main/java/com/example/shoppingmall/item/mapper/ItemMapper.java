package com.example.shoppingmall.item.mapper;

import com.example.shoppingmall.item.dto.ItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<ItemDTO> getAllItems();
}
