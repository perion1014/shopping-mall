package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long memberNo;
    private String memberName;
    private String memberAddressBasic;
    private String memberAddressDetail;
    private String memberHp;

    public static MemberDTO memberToMemberDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberNo(member.getMemberNo());
        memberDTO.setMemberName(member.getMemberName());
        memberDTO.setMemberAddressBasic(member.getMemberAddressBasic());
        memberDTO.setMemberAddressDetail(member.getMemberAddressDetail());
        memberDTO.setMemberHp(member.getMemberHp());
        return memberDTO;
    }
}
