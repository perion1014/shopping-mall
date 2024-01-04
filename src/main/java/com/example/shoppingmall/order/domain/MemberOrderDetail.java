package com.example.shoppingmall.order.domain;

import com.example.shoppingmall.order.dto.MemberOrderDTO;
import com.example.shoppingmall.order.dto.MemberOrderDetailAddDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberOrderDetail {

    private Long memberOrderDetailNo;
    private Long memberOrderNo;
    private Long itemNo;
    private Integer itemQuantity;
    private String itemSize;
    private String itemName;
    private Integer itemPrice;

    private MemberOrder memberOrder;
    private MemberOrderDTO memberOrderDTO;

}
