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
public class ItemUpdateDTO {
    private Long itemNo;
    private String itemName;
    private String itemCategory;
    private Double itemGrade;
    private String itemDetail;
    private Integer itemPrice;
    private Integer itemOnsale;


    public static Item itemUpdateDTOToItem(Long itemNo, ItemUpdateDTO itemUpdateDTO) {
        Item item = new Item();
        item.setItemNo(itemNo);
        item.setItemName(itemUpdateDTO.getItemName());
        item.setItemCategory(itemUpdateDTO.getItemCategory());
        item.setItemGrade(itemUpdateDTO.getItemGrade());
        item.setItemDetail(itemUpdateDTO.getItemDetail());
        item.setItemPrice(itemUpdateDTO.getItemPrice());
        item.setItemOnsale(itemUpdateDTO.getItemOnsale());
        return item;
    }

    public static ItemUpdateDTO itemToItemUpdateDTO(Item item) {
        ItemUpdateDTO itemUpdateDTO = new ItemUpdateDTO();
        itemUpdateDTO.setItemName(item.getItemName());
        itemUpdateDTO.setItemCategory(item.getItemCategory());
        itemUpdateDTO.setItemGrade(item.getItemGrade());
        itemUpdateDTO.setItemDetail(item.getItemDetail());
        itemUpdateDTO.setItemPrice(item.getItemPrice());
        itemUpdateDTO.setItemOnsale(item.getItemOnsale());
        return itemUpdateDTO;
    }
}
