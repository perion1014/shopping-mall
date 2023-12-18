package com.example.shoppingmall.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemStock {

    private Long itemStockNo;
    private Long itemNo;
    private String itemSize;
    private Integer itemStock;

}
