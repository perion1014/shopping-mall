package com.example.shoppingmall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberOrderItemStockCheckDTO {
    private Long nonMemberCartNo;
    private Long itemNo;
    private String itemSize;
    private Integer itemQuantity;
    private String itemName;
    private Integer itemPrice;
    private String itemThumbnail;
    private Boolean isPurchaseInItemDetailPage;
}