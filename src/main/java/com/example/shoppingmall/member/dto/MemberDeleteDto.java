package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDeleteDto {
    private Integer memberNo;
    private String memberPw;
    private String memberPw2;

    public static Member MemberDeleteDtoToMember(MemberDeleteDto memberDeleteDto){
        Member member = new Member();
        member.setMemberPw(memberDeleteDto.getMemberPw());
        return member;
    }
}
