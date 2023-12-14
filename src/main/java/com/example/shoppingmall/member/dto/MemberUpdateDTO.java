package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
    private String memberId;
    private String memberPw;
    private String memberPw2;
    private String memberHp;
    private String memberName;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;

    public static Member MemberUpdateDTOToMember(MemberUpdateDTO memberUpdateDTO){
        Member member = new Member();
        member.setMemberId(memberUpdateDTO.getMemberId());
        member.setMemberPw(memberUpdateDTO.getMemberPw());
        member.setMemberHp(memberUpdateDTO.getMemberHp());
        member.setMemberName(memberUpdateDTO.getMemberName());
        member.setMemberPostalCode(memberUpdateDTO.getMemberPostalCode());
        member.setMemberAddressBasic(memberUpdateDTO.getMemberAddressBasic());
        member.setMemberAddressDetail(memberUpdateDTO.getMemberAddressDetail());
        return member;
    }
}
