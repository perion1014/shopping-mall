package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddDto {

    private String memberId;
    private String memberPw;
    private String memberPw2;
    private String memberHp;
    private String memberName;
    private String memberEmail;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;


    public static Member MemberAddDtoToMember(MemberAddDto memberAddDto){
        Member member = new Member();
        member.setMemberId(memberAddDto.getMemberId());
        member.setMemberHp(memberAddDto.getMemberHp());
        member.setMemberEmail(memberAddDto.getMemberEmail());
        member.setMemberPw(memberAddDto.getMemberPw());
        member.setMemberName(memberAddDto.getMemberName());
        member.setMemberPostalCode(memberAddDto.getMemberPostalCode());
        member.setMemberAddressBasic(memberAddDto.getMemberAddressBasic());
        member.setMemberAddressDetail(memberAddDto.getMemberAddressDetail());
        return member;
    }
}
