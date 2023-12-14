package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {
    private String memberId;
    private String memberPw;

    public static Member MemberLoginDtoToMember(MemberLoginDto memberLoginDto){
        Member member = new Member();
        member.setMemberId(memberLoginDto.getMemberId());
        member.setMemberPw(memberLoginDto.getMemberPw());
        return member;
    }
}
