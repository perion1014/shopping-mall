package com.example.shoppingmall.order.domain;

import com.example.shoppingmall.order.dto.MemberOrderDetailDTO;
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
public class MemberOrder {

    private Long memberOrderNo;
    private Long memberNo;
    private Timestamp orderTime;
    private String orderHp;
    private Integer orderPostalCode;
    private String orderAddressBasic;
    private String orderAddressDetail;
    private String receiverName;
    private Integer orderStatus;    //0: 주문 취소, 1: 주문 완료, default 값 = 1

    private List<MemberOrderDetail> memberOrderDetailList;
    private List<MemberOrderDetailDTO> memberOrderDetailDTOList;
}
