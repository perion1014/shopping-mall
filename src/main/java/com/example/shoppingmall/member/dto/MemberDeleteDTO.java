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
    private Long memberNo;
    private String memberPw;
    private String memberPw2;

    public static Member MemberDeleteDTOToMember(Long memberNo,MemberDeleteDTO memberDeleteDTO){
        Member member = new Member();
        member.setMemberNo(memberNo);
        member.setMemberPw(memberDeleteDTO.getMemberPw());
        return member;
    }
}
