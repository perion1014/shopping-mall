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
    private Long memberNo;
    private String memberEmail;
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

    public static MemberUpdateDTO MemberToMemberUpdateDTO(Member member){
        MemberUpdateDTO memberUpdateDTO = new MemberUpdateDTO();
        memberUpdateDTO.setMemberId(member.getMemberId());
        memberUpdateDTO.setMemberEmail(member.getMemberEmail());
        memberUpdateDTO.setMemberHp(member.getMemberHp());
        memberUpdateDTO.setMemberName(member.getMemberName());
        memberUpdateDTO.setMemberPostalCode(member.getMemberPostalCode());
        memberUpdateDTO.setMemberAddressBasic(member.getMemberAddressBasic());
        memberUpdateDTO.setMemberAddressDetail(member.getMemberAddressDetail());
        return memberUpdateDTO;
    }


}
