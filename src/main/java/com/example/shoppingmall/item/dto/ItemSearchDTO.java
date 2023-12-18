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
public class ItemSearchDTO {
    private String itemName;

    public static Item itemSearchDTOToItem(ItemSearchDTO itemSearchDTO) {
        Item item = new Item();
        item.setItemName(itemSearchDTO.getItemName());
        return item;
    }
}
