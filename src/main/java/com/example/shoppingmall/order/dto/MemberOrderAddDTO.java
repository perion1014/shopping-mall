package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.MemberOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberOrderAddDTO {

    private Long memberOrderNo;
    private Long memberNo;
    private Timestamp orderTime;
    private String orderHp;
    private Integer orderPostalCode;
    private String orderAddressBasic;
    private String orderAddressDetail;
    private String receiverName;
    private Integer orderStatus;

    public static MemberOrder toMemberOrder(MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberOrderNo(memberOrderAddDTO.getMemberOrderNo());
        memberOrder.setMemberNo(memberOrderAddDTO.getMemberNo());
        memberOrder.setOrderTime(memberOrderAddDTO.getOrderTime());
        memberOrder.setOrderHp(memberOrderAddDTO.getOrderHp());
        memberOrder.setOrderPostalCode(memberOrderAddDTO.getOrderPostalCode());
        memberOrder.setOrderAddressBasic(memberOrderAddDTO.getOrderAddressBasic());
        memberOrder.setOrderAddressDetail(memberOrderAddDTO.getOrderAddressDetail());
        memberOrder.setReceiverName(memberOrderAddDTO.getReceiverName());
        memberOrder.setOrderStatus(memberOrderAddDTO.getOrderStatus());
        return memberOrder;
    }
}
