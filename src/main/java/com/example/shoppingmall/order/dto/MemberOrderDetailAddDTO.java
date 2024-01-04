package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.MemberOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public static MemberOrderDetail toMemberOrderDetail(MemberOrderDetailAddDTO memberOrderDetailAddDTO) {
        MemberOrderDetail memberOrderDetail = new MemberOrderDetail();
        memberOrderDetail.setMemberOrderDetailNo(memberOrderDetailAddDTO.getMemberOrderDetailNo());
        memberOrderDetail.setMemberOrderNo(memberOrderDetailAddDTO.getMemberOrderNo());
        memberOrderDetail.setItemNo(memberOrderDetailAddDTO.getItemNo());
        memberOrderDetail.setItemQuantity(memberOrderDetailAddDTO.getItemQuantity());
        memberOrderDetail.setItemSize(memberOrderDetailAddDTO.getItemSize());
        return memberOrderDetail;
    }
}
