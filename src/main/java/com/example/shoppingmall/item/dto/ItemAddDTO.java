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
public class ItemAddDTO {
    private Long itemNo;
    private String itemName;
    private String itemCategory;
    private Integer itemPrice;
    private Integer itemOnsale;
    private String itemSize;
    private Integer itemStock;
    private String itemDetail;
    private Timestamp itemRegisteredTime;

    public static Item itemAddDTOToItem(ItemAddDTO itemAddDTO) {
        Item item = new Item();
        item.setItemNo(itemAddDTO.getItemNo());
        item.setItemName(itemAddDTO.getItemName());
        item.setItemCategory(itemAddDTO.getItemCategory());
        item.setItemPrice(itemAddDTO.getItemPrice());
        item.setItemOnsale(itemAddDTO.getItemOnsale());
        item.setItemDetail(itemAddDTO.getItemDetail());
        item.setItemRegisteredTime(itemAddDTO.getItemRegisteredTime());
        return item;
    }
    public static ItemAddDTO itemToItemAddDTO(Item item) {
        ItemAddDTO itemAddDTO = new ItemAddDTO();
        itemAddDTO.setItemNo(item.getItemNo());
        itemAddDTO.setItemName(item.getItemName());
        itemAddDTO.setItemCategory(item.getItemCategory());
        itemAddDTO.setItemPrice(item.getItemPrice());
        itemAddDTO.setItemPrice((item.getItemOnsale()));
        itemAddDTO.setItemDetail(item.getItemDetail());
        itemAddDTO.setItemRegisteredTime(item.getItemRegisteredTime());
        return itemAddDTO;
    }

}
