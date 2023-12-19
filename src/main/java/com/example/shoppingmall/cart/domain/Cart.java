package com.example.shoppingmall.cart.domain;

import com.example.shoppingmall.cart.dto.CartReadDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long cartNo;
    private Long memberNo;
    private Long itemNo;
    private Integer cartItemQuantity;
    private String itemSize;
}