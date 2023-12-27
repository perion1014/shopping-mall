package com.example.shoppingmall.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class nonMemberCartAddDTO {

    private Long cartNo;
    private String itemThumbnail;
    private String itemName;
    private String itemSize;
    private Integer itemPrice;
    private Integer itemQuantity;

}
