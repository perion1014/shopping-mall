package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.MemberOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    private String itemThumb;
    private Integer reviewCount;


    public static MemberOrderDetailDTO toMemberOrderDetailDTO(Long memberOrderNo, MemberOrderDetail memberOrderDetail) {
        MemberOrderDetailDTO memberOrderDetailDTO = new MemberOrderDetailDTO();
        memberOrderDetailDTO.setMemberOrderDetailNo(memberOrderDetail.getMemberOrderDetailNo());
        memberOrderDetailDTO.setMemberOrderNo(memberOrderNo);
        memberOrderDetailDTO.setItemNo(memberOrderDetail.getItemNo());
        memberOrderDetailDTO.setItemQuantity(memberOrderDetail.getItemQuantity());
        memberOrderDetailDTO.setItemSize(memberOrderDetail.getItemSize());
        memberOrderDetailDTO.setItemName(memberOrderDetail.getItemName());
        memberOrderDetailDTO.setItemPrice(memberOrderDetail.getItemPrice());
        return memberOrderDetailDTO;
    }

    public static List<MemberOrderDetailDTO> toMemberOrderDetailDTOList(Long memberOrderNo, List<MemberOrderDetail> memberOrderDetailList) {
        List<MemberOrderDetailDTO> memberOrderDetailDTOList = new ArrayList<>();
        for (MemberOrderDetail memberOrderDetail: memberOrderDetailList) {
            memberOrderDetailDTOList.add(MemberOrderDetailDTO.toMemberOrderDetailDTO(memberOrderNo, memberOrderDetail));
        }
        return memberOrderDetailDTOList;
    }
}
