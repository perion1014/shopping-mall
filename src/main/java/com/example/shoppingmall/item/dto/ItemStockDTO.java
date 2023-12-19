package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemStockDTO {

    private Long itemStockNo;
    private Long itemNo;
    private String itemSize;
    private Integer itemStock;

    public static ItemStockDTO toItemStock(ItemStock itemStock) {
        ItemStockDTO itemStockDTO = new ItemStockDTO();
        itemStockDTO.setItemStockNo(itemStock.getItemStockNo());
        itemStockDTO.setItemNo(itemStock.getItemNo());
        itemStockDTO.setItemSize(itemStock.getItemSize());
        itemStockDTO.setItemStock(itemStock.getItemStock());
        return itemStockDTO;
    }

}
