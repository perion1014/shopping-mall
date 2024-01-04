package com.example.shoppingmall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonMemberOrderAddDTO {

    private Long nonMemberOrderNo;
    private String nonMemberName;
    private Timestamp orderTime;
    private String orderHp;
    private String orderEmail;
    private String receiverName;
    private Integer orderPostalCode;
    private String orderAddressBasic;
    private String orderAddressDetail;
    private Integer orderStatus;

}
