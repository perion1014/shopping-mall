package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPw;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPw2;
    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String memberHp;
    @NotBlank(message = "성명을 입력해주세요.")
    private String memberName;
    @NotBlank(message = "이메일을 입력해주세요.")
    private String memberEmail;
    private Integer memberPostalCode;
    @NotBlank(message = "기본번호를 입력해주세요.")
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
