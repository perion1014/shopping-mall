package com.example.shoppingmall.item.domain;

import com.example.shoppingmall.item.dto.ItemAddDTO;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.dto.ItemDeleteDTO;
import com.example.shoppingmall.item.dto.ItemUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long itemNo;
    private String itemName;
    private String itemCategory;
    private Double itemGrade;
    private String itemDetail;
    private Integer itemPrice;
    private Integer itemOnsale;
    private Timestamp itemRegisteredTime;

    public static Item itemDTOToItemWithItemNo(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemNo(itemDTO.getItemNo());
        item.setItemName(itemDTO.getItemName());
        item.setItemCategory(itemDTO.getItemCategory());
        item.setItemGrade(itemDTO.getItemGrade());
        item.setItemDetail(itemDTO.getItemDetail());
        item.setItemPrice(itemDTO.getItemPrice());
        item.setItemOnsale(itemDTO.getItemOnsale());
        item.setItemRegisteredTime(itemDTO.getItemRegisteredTime());
        return item;
    }


}
