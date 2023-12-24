package com.example.shoppingmall.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemItemStock {

    private Long itemNo;
    private String itemName;
    private Integer itemPrice;

    private ItemStock itemStock;

    private String itemCategory;
    private Integer itemOnsale;

}
