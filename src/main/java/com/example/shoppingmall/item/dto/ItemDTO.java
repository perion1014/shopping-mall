package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long itemNo;
    private String itemName;
    private String itemCategory;
    private Double itemGrade;
    private String itemDetail;
    private Integer itemPrice;
    private Integer itemOnsale;
    private Timestamp itemRegisteredTime;

    public static ItemDTO toItemDTO(Item item) {
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
