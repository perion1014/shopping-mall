package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.NonMemberOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonMemberOrderDetailAddDTO {
    private Long itemNo;
    private String itemName;
    private String itemSize;
    private Integer itemQuantity;
    private Integer itemPrice;

    public static NonMemberOrderDetail toNonMemberOrderDetail(NonMemberOrderDetailAddDTO nonMemberOrderDetailAddDTO, Long nonMemberOrderNo){
        NonMemberOrderDetail nonMemberOrderDetail = new NonMemberOrderDetail();
        nonMemberOrderDetail.setNonMemberOrderDetailNo(null);
        nonMemberOrderDetail.setNonMemberOrderNo(nonMemberOrderNo);
        nonMemberOrderDetail.setItemNo(nonMemberOrderDetailAddDTO.getItemNo());
        nonMemberOrderDetail.setItemQuantity(nonMemberOrderDetailAddDTO.getItemQuantity());
        nonMemberOrderDetail.setItemSize(nonMemberOrderDetailAddDTO.getItemSize());
        nonMemberOrderDetail.setItemName(nonMemberOrderDetailAddDTO.getItemName());
        nonMemberOrderDetail.setItemPrice(nonMemberOrderDetailAddDTO.getItemPrice());
        return nonMemberOrderDetail;
    }
}