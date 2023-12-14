package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDto {
    private Integer memberNo;
    private String memberPw;
    private String memberPw2;
    private String memberHp;
    private String memberName;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;

    public static Member MemberUpdateDtoToMember(MemberUpdateDto memberUpdateDto){
        Member member = new Member();
        member.setMemberPw(memberUpdateDto.getMemberPw());
        member.setMemberHp(memberUpdateDto.getMemberHp());
        member.setMemberName(memberUpdateDto.getMemberName());
        member.setMemberPostalCode(memberUpdateDto.getMemberPostalCode());
        member.setMemberAddressBasic(memberUpdateDto.getMemberAddressBasic());
        member.setMemberAddressDetail(memberUpdateDto.getMemberAddressDetail());
        return member;
    }
}
