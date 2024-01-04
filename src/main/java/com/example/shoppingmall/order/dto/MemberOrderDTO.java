package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberOrderDTO {

    private Long memberOrderNo;
    private Long memberNo;
    private Timestamp orderTime;
    private String orderHp;
    private Integer orderPostalCode;
    private String orderAddressBasic;
    private String orderAddressDetail;
    private String receiverName;
    private Integer orderStatus;

    private List<MemberOrderDetailDTO> memberOrderDetailDTOList;

    public static MemberOrderDTO toMemberOrderDTO(MemberOrder memberOrder) {
        MemberOrderDTO memberOrderDTO = new MemberOrderDTO();
        memberOrderDTO.setMemberOrderNo(memberOrder.getMemberOrderNo());
        memberOrderDTO.setMemberNo(memberOrder.getMemberNo());
        memberOrderDTO.setOrderTime(memberOrder.getOrderTime());
        memberOrderDTO.setOrderHp(memberOrder.getOrderHp());
        memberOrderDTO.setOrderPostalCode(memberOrder.getOrderPostalCode());
        memberOrderDTO.setOrderAddressBasic(memberOrder.getOrderAddressBasic());
        memberOrderDTO.setOrderAddressDetail(memberOrder.getOrderAddressDetail());
        memberOrderDTO.setReceiverName(memberOrder.getReceiverName());
        memberOrderDTO.setOrderStatus(memberOrder.getOrderStatus());
        memberOrderDTO.setMemberOrderDetailDTOList(MemberOrderDTO.toMemberOrderDetailDTOList(memberOrder.getMemberOrderDetailList()));
        return memberOrderDTO;
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
            memberOrderDetailDTOList.add(memberOrderDetailDTO);
        }
        return memberOrderDetailDTOList;
    }
}
