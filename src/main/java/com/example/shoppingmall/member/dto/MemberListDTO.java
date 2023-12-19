package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberListDTO {
    private Long memberNo;
    private String memberId;
    private String memberName;
    private String memberHp;
    private String memberEmail;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;

    public static MemberListDTO MemberToMemberListDTO(Member member){
        MemberListDTO memberListDTO = new MemberListDTO();
        memberListDTO.setMemberNo(member.getMemberNo());
        memberListDTO.setMemberId(member.getMemberId());
        memberListDTO.setMemberName(member.getMemberName());
        memberListDTO.setMemberHp(member.getMemberHp());
        memberListDTO.setMemberEmail(memberListDTO.getMemberEmail());
        memberListDTO.setMemberPostalCode(memberListDTO.getMemberPostalCode());
        memberListDTO.setMemberAddressBasic(memberListDTO.getMemberAddressBasic());
        memberListDTO.setMemberAddressDetail(memberListDTO.getMemberAddressDetail());
        return memberListDTO;
    }

}
