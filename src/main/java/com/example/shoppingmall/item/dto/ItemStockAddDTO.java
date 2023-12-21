package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemStockAddDTO {
    private Long itemStockNo;
    private Long itemNo;
    private String itemSize;
    private Integer itemStockValue;

    public static ItemStock toItemStock(ItemStockAddDTO itemStockAddDTO) {
        ItemStock itemstock = new ItemStock();
        itemstock.setItemStockNo(itemStockAddDTO.getItemStockNo());
        itemstock.setItemNo(itemStockAddDTO.getItemNo());
        itemstock.setItemSize(itemStockAddDTO.getItemSize());
        itemstock.setItemStockValue(itemStockAddDTO.getItemStockValue());
        return itemstock;
    }

    public static ItemStockAddDTO toItemStockAddDTO(ItemStock itemStock) {
        ItemStockAddDTO itemStockAddDTO = new ItemStockAddDTO();
        itemStockAddDTO.setItemStockNo(itemStock.getItemStockNo());
        itemStockAddDTO.setItemNo(itemStock.getItemNo());
        itemStockAddDTO.setItemSize(itemStock.getItemSize());
        itemStockAddDTO.setItemStockValue(itemStock.getItemStockValue());
        return itemStockAddDTO;
    }
}
