package com.example.shoppingmall.order.dto;

import com.example.shoppingmall.order.domain.MemberOrder;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

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

    private List<MemberOrderDetailDTO> memberOrderDetailDTOList;
    private List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList;

    public static MemberOrder toMemberOrder(Long memberNo, MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberOrderNo(memberOrderAddDTO.getMemberOrderNo());
        memberOrder.setMemberNo(memberNo);
        memberOrder.setOrderTime(memberOrderAddDTO.getOrderTime());
        memberOrder.setOrderHp(memberOrderAddDTO.getOrderHp());
        memberOrder.setOrderPostalCode(memberOrderAddDTO.getOrderPostalCode());
        memberOrder.setOrderAddressBasic(memberOrderAddDTO.getOrderAddressBasic());
        memberOrder.setOrderAddressDetail(memberOrderAddDTO.getOrderAddressDetail());
        memberOrder.setReceiverName(memberOrderAddDTO.getReceiverName());
        memberOrder.setOrderStatus(1);
        //memberOrder.setMemberOrderDetailDTOList(memberOrderAddDTO.getMemberOrderDetailDTOList());
        return memberOrder;
    }

    public static MemberOrderDTO toMemberOrderDTO(MemberOrderAddDTO memberOrderAddDTO) {
        MemberOrderDTO memberOrderDTO = new MemberOrderDTO();
        memberOrderDTO.setMemberOrderNo(memberOrderAddDTO.getMemberOrderNo());
        memberOrderDTO.setMemberNo(memberOrderAddDTO.getMemberNo());
        memberOrderDTO.setOrderTime(memberOrderAddDTO.getOrderTime());
        memberOrderDTO.setOrderHp(memberOrderAddDTO.getOrderHp());
        memberOrderDTO.setOrderPostalCode(memberOrderAddDTO.getOrderPostalCode());
        memberOrderDTO.setOrderAddressBasic(memberOrderAddDTO.getOrderAddressBasic());
        memberOrderDTO.setOrderAddressDetail(memberOrderAddDTO.getOrderAddressDetail());
        memberOrderDTO.setReceiverName(memberOrderAddDTO.getReceiverName());
        memberOrderDTO.setOrderStatus(memberOrderAddDTO.getOrderStatus());
        return memberOrderDTO;
    }



//    public static MemberOrderDetail toMemberOrderDetail(MemberOrderAddDTO memberOrderAddDTO) {
//        MemberOrderDetail memberOrderDetail = new MemberOrderDetail();
//        memberOrderDetail.setMemberOrderDetailNo(memberOrderAddDTO.getMemberOrderDetailNo());
//        memberOrderDetail.setItemNo(memberOrderAddDTO.getItemNo());
//        memberOrderDetail.setMemberOrderNo(memberOrderAddDTO.getMemberOrderNo());
//        memberOrderDetail.setItemStockNo(memberOrderAddDTO.getItemStockNo());
//        memberOrderDetail.setItemQuantity(memberOrderAddDTO.getItemQuantity());
//        return memberOrderDetail;
//    }
}
