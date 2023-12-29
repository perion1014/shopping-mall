package com.example.shoppingmall.cart.dto;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.service.CartService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartReadDTO {
    private Long memberNo;
    private Long cartNo;
    private Long itemNo;
    private String itemThumbnail;
    private String itemName;
    private String itemSize;
    private Integer itemPrice;
    private Integer itemQuantity;
    //private Integer itemPriceSum;
    //private Integer orderPriceSum;
}
