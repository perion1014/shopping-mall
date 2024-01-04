package com.example.shoppingmall.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonMemberOrder {

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
