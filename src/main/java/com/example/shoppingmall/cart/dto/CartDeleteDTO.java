package com.example.shoppingmall.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDeleteDTO {

    private Long memberNo;
    private Long itemNo;
    private String itemSize;

}
