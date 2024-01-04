package com.example.shoppingmall.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonMemberOrderDetail {

    private Long nonMemberOrderDetailNo;
    private Long nonMemberOrderNo;
    private Long itemNo;
    private Integer itemQuantity;
    private String itemSize;

}
