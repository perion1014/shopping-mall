package com.example.shoppingmall.member.domain;

import com.example.shoppingmall.order.domain.MemberOrderDetail;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberNo;
    private String memberId;
    private String memberHp;
    private String memberEmail;
    private String memberPw;
    private String memberName;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;

    private List<MemberOrderDetail> memberOrderDetailList;
}
