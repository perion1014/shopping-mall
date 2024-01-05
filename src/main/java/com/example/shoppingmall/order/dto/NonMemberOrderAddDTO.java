package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.NonMemberOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonMemberOrderAddDTO {

    private String nonMemberName;
    private String orderHp;
    private String orderEmail;
    private String receiverName;
    private Integer orderPostalCode;
    private String orderAddressBasic;
    private String orderAddressDetail;

    public static NonMemberOrder toNonMemberOrder(NonMemberOrderAddDTO nonMemberOrderAddDTO){
        NonMemberOrder nonMemberOrder = new NonMemberOrder();
        nonMemberOrder.setNonMemberOrderNo(null);
        nonMemberOrder.setOrderTime(null);
        nonMemberOrder.setOrderStatus(null);
        nonMemberOrder.setNonMemberName(nonMemberOrderAddDTO.getNonMemberName());
        nonMemberOrder.setOrderHp(nonMemberOrderAddDTO.getOrderHp());
        nonMemberOrder.setOrderEmail(nonMemberOrderAddDTO.getOrderEmail());
        nonMemberOrder.setReceiverName(nonMemberOrderAddDTO.getReceiverName());
        nonMemberOrder.setOrderPostalCode(nonMemberOrderAddDTO.getOrderPostalCode());
        nonMemberOrder.setOrderAddressBasic(nonMemberOrderAddDTO.getOrderAddressBasic());
        nonMemberOrder.setOrderAddressDetail(nonMemberOrderAddDTO.getOrderAddressDetail());
        return nonMemberOrder;
    }

}