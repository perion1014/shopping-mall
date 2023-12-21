package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchDTO {
    private Long memberNo;
    private String memberId;
    private String memberName;
    private String memberHp;
    private String memberEmail;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;

    public static MemberSearchDTO fromEntity(Member member){
        MemberSearchDTO memberSearchDTO = new MemberSearchDTO();
        memberSearchDTO.setMemberNo(member.getMemberNo());
        memberSearchDTO.setMemberId(member.getMemberId());
        memberSearchDTO.setMemberName(member.getMemberName());
        memberSearchDTO.setMemberHp(member.getMemberHp());
        memberSearchDTO.setMemberEmail(member.getMemberEmail());
        memberSearchDTO.setMemberPostalCode(member.getMemberPostalCode());
        memberSearchDTO.setMemberAddressBasic(member.getMemberAddressBasic());
        memberSearchDTO.setMemberAddressDetail(member.getMemberAddressDetail());
        return memberSearchDTO;
    }

}
