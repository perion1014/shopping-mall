package com.example.shoppingmall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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
}
