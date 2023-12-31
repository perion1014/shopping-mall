package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long itemNo;
    private String itemName;
    private Integer itemPrice;

    private String itemCategory;
    private Double itemGrade;
    private Integer itemOnsale;

    private String itemDetail; // 필요 x
    private Timestamp itemRegisteredTime; // 필요 x

    // 1:1 관계
    private ItemPhotosDTO itemPhotosDTO;

    // 1:N 관계
    private List<ItemStockDTO> itemStockDTOList;


    public static ItemDTO itemToItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemNo(item.getItemNo());
        itemDTO.setItemName(item.getItemName());
        itemDTO.setItemCategory(item.getItemCategory());
        itemDTO.setItemGrade(item.getItemGrade());
        itemDTO.setItemDetail(item.getItemDetail());
        itemDTO.setItemPrice(item.getItemPrice());
        itemDTO.setItemOnsale(item.getItemOnsale());
        itemDTO.setItemRegisteredTime(item.getItemRegisteredTime());
        itemDTO.setItemPhotosDTO(item.getItemPhotosDTO());
        itemDTO.setItemStockDTOList(ItemStockDTO.toItemStockDTOList(item.getItemStockList()));
        return itemDTO;
    }

    public static ItemDTO itemToItemDTO2(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemNo(item.getItemNo());
        itemDTO.setItemName(item.getItemName());
        itemDTO.setItemCategory(item.getItemCategory());
        itemDTO.setItemGrade(item.getItemGrade());
        itemDTO.setItemDetail(item.getItemDetail());
        itemDTO.setItemPrice(item.getItemPrice());
        itemDTO.setItemOnsale(item.getItemOnsale());
        itemDTO.setItemRegisteredTime(item.getItemRegisteredTime());
        return itemDTO;
    }


}
