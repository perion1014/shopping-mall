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
    private Integer itemQuantity;
    private String itemSize;
    private String itemName;
    private Integer itemPrice;


    public static MemberOrderDetailDTO toMemberOrderDetailDTO(MemberOrderDetail memberOrderDetail) {
        MemberOrderDetailDTO memberOrderDetailDTO = new MemberOrderDetailDTO();
        memberOrderDetailDTO.setMemberOrderDetailNo(memberOrderDetail.getMemberOrderDetailNo());
        memberOrderDetailDTO.setMemberOrderNo(memberOrderDetail.getMemberOrderNo());
        memberOrderDetailDTO.setItemNo(memberOrderDetail.getItemNo());
        memberOrderDetailDTO.setItemQuantity(memberOrderDetail.getItemQuantity());
        memberOrderDetailDTO.setItemSize(memberOrderDetail.getItemSize());
        memberOrderDetailDTO.setItemSize(memberOrderDetailDTO.getItemSize());
        memberOrderDetailDTO.setItemPrice(memberOrderDetailDTO.getItemPrice());
        return memberOrderDetailDTO;
    }
}
