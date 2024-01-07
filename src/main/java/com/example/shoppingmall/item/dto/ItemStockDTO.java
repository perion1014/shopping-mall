package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemStockDTO {

    private Long itemStockNo;
    private Long itemNo;
    private String itemSize;
    private Integer itemStockValue;

    public static ItemStockDTO toItemStockDTO(ItemStock itemStock) {
        ItemStockDTO itemStockDTO = new ItemStockDTO();
        itemStockDTO.setItemStockNo(itemStock.getItemStockNo());
        itemStockDTO.setItemNo(itemStock.getItemNo());
        itemStockDTO.setItemSize(itemStock.getItemSize());
        itemStockDTO.setItemStockValue(itemStock.getItemStockValue());
        return itemStockDTO;
    }

    public static List<ItemStockDTO> toItemStockDTOList(List<ItemStock> itemStockList) {
        List<ItemStockDTO> itemStockDTOList = new ArrayList<>();
        for (ItemStock itemStock: itemStockList) {
            itemStockDTOList.add(ItemStockDTO.toItemStockDTO(itemStock));
        }
        return itemStockDTOList;
    }

}
