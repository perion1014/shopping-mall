package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddDTO {

    private String memberId;
    private String memberPw;
    private String memberPw2;
    private String memberHp;
    private String memberName;
    private String memberEmail;
    private Integer memberAddCode;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;


    public static Member MemberAddDTOToMember(MemberAddDTO memberAddDTO){
        Member member = new Member();
        member.setMemberId(memberAddDTO.getMemberId());
        member.setMemberHp(memberAddDTO.getMemberHp());
        member.setMemberEmail(memberAddDTO.getMemberEmail());
        member.setMemberPw(memberAddDTO.getMemberPw());
        member.setMemberName(memberAddDTO.getMemberName());
        member.setMemberPostalCode(memberAddDTO.getMemberPostalCode());
        member.setMemberAddressBasic(memberAddDTO.getMemberAddressBasic());
        member.setMemberAddressDetail(memberAddDTO.getMemberAddressDetail());
        return member;
    }
}
