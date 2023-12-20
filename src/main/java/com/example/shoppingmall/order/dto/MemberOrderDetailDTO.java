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
public class MemberOrderDetailDTO {

    private Long memberOrderDetailNo;
    private Long memberOrderNo;
    private Long itemNo;
    private Long itemStockNo;
    private Integer itemQuantity;

    public static MemberOrderDetailDTO toMemberOrderDetailDTO(MemberOrderDetail memberOrderDetail) {
        MemberOrderDetailDTO memberOrderDetailDTO = new MemberOrderDetailDTO();
        memberOrderDetailDTO.setMemberOrderDetailNo(memberOrderDetail.getMemberOrderDetailNo());
        memberOrderDetailDTO.setMemberOrderNo(memberOrderDetail.getMemberOrderNo());
        memberOrderDetailDTO.setItemNo(memberOrderDetail.getItemNo());
        memberOrderDetailDTO.setItemStockNo(memberOrderDetail.getItemStockNo());
        memberOrderDetailDTO.setItemQuantity(memberOrderDetail.getItemQuantity());
        return memberOrderDetailDTO;
    }
}
