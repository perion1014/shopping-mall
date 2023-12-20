package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDeleteDTO {

    private Long itemNo;
    private Long itemStockNo;
    private String itemName;
    private String itemThumb;
    private Integer itemStock;

    public static Item toItem(Long itemNo, ItemDeleteDTO itemDeleteDTO) {
        Item item = new Item();
        item.setItemNo(itemNo);
        item.setItemName(itemDeleteDTO.getItemName());
        return item;
    }

    public static ItemPhotos toItemPhotos(Long itemNo, ItemDeleteDTO itemDeleteDTO) {
        ItemPhotos itemPhotos = new ItemPhotos();
        itemPhotos.setItemNo(itemNo);
        itemPhotos.setItemThumb(itemDeleteDTO.getItemThumb());
        return itemPhotos;
    }

    // 개별 삭제를 위한 메서드
    public static ItemStock toItemStockByItemStockNo(Long itemStockNo, ItemDeleteDTO itemDeleteDTO) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemStockNo(itemStockNo);
        itemStock.setItemStock(itemDeleteDTO.getItemStock());
        return itemStock;
    }

    // 연쇄 삭제를 위한 메서드
    public static ItemStock toItemStockByItemNo(Long itemNo, ItemDeleteDTO itemDeleteDTO) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemNo(itemNo);
        itemStock.setItemStock(itemDeleteDTO.getItemStock());
        return itemStock;
    }

    public static ItemDeleteDTO itemToItemDeleteDTO(Item item) {
        ItemDeleteDTO itemDeleteDTO = new ItemDeleteDTO();
        itemDeleteDTO.setItemNo(item.getItemNo());
        itemDeleteDTO.setItemName(item.getItemName());
        return itemDeleteDTO;
    }
}
