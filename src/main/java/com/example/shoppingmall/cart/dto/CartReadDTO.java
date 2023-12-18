package com.example.shoppingmall.cart.dto;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.service.CartService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartReadDTO {
    private String itemThumbnail;
    private String itemName;
    private String itemSize;
    private Integer itemPrice;
    private Integer itemQuantity;
    private Integer itemPriceSum;
    private Integer orderPriceSum;

    public static CartReadDTO CartToCartDTO(Cart cart){
    CartReadDTO cartReadDTO = new CartReadDTO();
    cartReadDTO.setItemThumbnail(CartService.getItemThumbnail(cart.getItemNo())); // item_photos에서 가져옴
    cartReadDTO.setItemName(CartService.getItemName(cart.getItemNo()));           // item에서 가져옴
    cartReadDTO.setItemSize(CartService.getItemSize());           // cart에서 가져옴
    cartReadDTO.setItemPrice(CartService.getItemPrice(cart.getItemNo()));         // item에서 가져옴
    cartReadDTO.setItemQuantity(CartService.getItemQuantity());   // cart에서 가져옴
    cartReadDTO.setItemPriceSum(CartService.getItemPriceSum());   // itemPrice로 계산
    cartReadDTO.setOrderPriceSum(CartService.getOrderPriceSum()); // otemPriceSum으로 계산
    return cartReadDTO;
    }

}
