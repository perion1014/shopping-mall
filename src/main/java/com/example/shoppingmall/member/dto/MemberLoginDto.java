package com.example.shoppingmall.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {
    private Integer memberNo;
    private String memberId;
    private String memberPw;
}
