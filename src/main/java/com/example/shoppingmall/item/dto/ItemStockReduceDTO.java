package com.example.shoppingmall.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemStockReduceDTO {
    private Long itemNo;
    private String itemSize;
    private Integer itemQuantity;
}
