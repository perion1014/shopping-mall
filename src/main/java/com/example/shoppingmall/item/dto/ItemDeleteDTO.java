package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDeleteDTO {

    private Long itemNo;
    private String itemName;

    public static Item ItemDeleteDTOToItem(ItemDeleteDTO itemDeleteDTO) {
        Item item = new Item();
        item.setItemNo(itemDeleteDTO.getItemNo());
        item.setItemName(itemDeleteDTO.getItemName());
        return item;
    }

    public static ItemDeleteDTO itemToItemDeleteDTO(Item item) {
        ItemDeleteDTO itemDeleteDTO = new ItemDeleteDTO();
        itemDeleteDTO.setItemNo(item.getItemNo());
        itemDeleteDTO.setItemName(item.getItemName());
        return itemDeleteDTO;
    }
}
