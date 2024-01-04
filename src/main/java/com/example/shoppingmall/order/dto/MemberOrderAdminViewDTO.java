package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.MemberOrder;
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
public class MemberOrderAdminViewDTO {

    private Long memberOrderNo;
    private Long memberNo;
    private String receiverName;
    private String orderHp;
    private Integer orderPostalCode;
    private String orderAddressBasic;
    private String orderAddressDetail;
    private Integer orderStatus;

    private List<MemberOrderDetailDTO> memberOrderDetailDTOList;

    public static MemberOrderAdminViewDTO toMemberOrderAdminViewDTO(MemberOrder memberOrder) {
        MemberOrderAdminViewDTO memberOrderAdminViewDTO = new MemberOrderAdminViewDTO();
        memberOrderAdminViewDTO.setMemberOrderNo(memberOrder.getMemberOrderNo());
        memberOrderAdminViewDTO.setMemberNo(memberOrder.getMemberNo());
        memberOrderAdminViewDTO.setReceiverName(memberOrder.getReceiverName());
        memberOrderAdminViewDTO.setOrderHp(memberOrder.getOrderHp());
        memberOrderAdminViewDTO.setOrderPostalCode(memberOrder.getOrderPostalCode());
        memberOrderAdminViewDTO.setOrderAddressBasic(memberOrder.getOrderAddressBasic());
        memberOrderAdminViewDTO.setOrderAddressDetail(memberOrder.getOrderAddressDetail());
        memberOrderAdminViewDTO.setOrderStatus(memberOrder.getOrderStatus());
        memberOrderAdminViewDTO.setMemberOrderDetailDTOList(MemberOrderAdminViewDTO.toMemberOrderDetailDTOList(memberOrder.getMemberOrderDetailList()));
        return memberOrderAdminViewDTO;
    }

    public static List<MemberOrderDetailDTO> toMemberOrderDetailDTOList(List<MemberOrderDetail> memberOrderDetailList) {
        List<MemberOrderDetailDTO> memberOrderDetailDTOList = new ArrayList<>();
        for (MemberOrderDetail memberOrderDetail: memberOrderDetailList) {
            MemberOrderDetailDTO memberOrderDetailDTO = new MemberOrderDetailDTO();
            memberOrderDetailDTO.setMemberOrderDetailNo(memberOrderDetail.getMemberOrderDetailNo());
            memberOrderDetailDTO.setMemberOrderNo(memberOrderDetail.getMemberOrderNo());
            memberOrderDetailDTO.setItemNo(memberOrderDetail.getItemNo());
            memberOrderDetailDTO.setItemQuantity(memberOrderDetail.getItemQuantity());
            memberOrderDetailDTO.setItemSize(memberOrderDetail.getItemSize());
            memberOrderDetailDTO.setItemName(memberOrderDetail.getItemName());
            memberOrderDetailDTO.setItemPrice(memberOrderDetail.getItemPrice());
        }
        return memberOrderDetailDTOList;
    }
}
