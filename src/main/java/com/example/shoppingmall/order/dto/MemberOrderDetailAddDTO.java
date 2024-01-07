package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberOrderDetailAddDTO {

    private Long memberOrderDetailNo;
    private Long memberOrderNo;
    private Long itemNo;
    private Integer itemQuantity;
    private String itemSize;

    private String itemName;
    private Integer itemPrice;
    private String itemThumb;

}
