package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDTO {
    private String memberId;
    private String memberPw;

    public static Member MemberLoginDTOToMember(MemberLoginDTO memberLoginDTO){
        Member member = new Member();
        member.setMemberId(memberLoginDTO.getMemberId());
        member.setMemberPw(memberLoginDTO.getMemberPw());
        return member;
    }
}
