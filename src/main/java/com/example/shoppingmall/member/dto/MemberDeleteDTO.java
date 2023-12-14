package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDeleteDTO {
    private String memberId;
    private String memberPw;
    private String memberPw2;

    public static Member MemberDeleteDTOToMember(MemberDeleteDTO memberDeleteDTO){
        Member member = new Member();
        member.setMemberId(memberDeleteDTO.getMemberId());
        member.setMemberPw(memberDeleteDTO.getMemberPw());
        return member;
    }
}
