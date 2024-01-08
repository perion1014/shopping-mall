package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static com.example.shoppingmall.member.validation.MemberValidationGroup.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPwDTO {
    @NotBlank(message = "아이디를 입력해주세요", groups = MemberNotBlankGroup.class)
    private String memberId;
    @NotBlank(message = "이메일을 입력해주세요", groups = MemberNotBlankGroup.class)
    private String memberEmail;
    @NotBlank(message = "핸드폰번호를 입력해주세요", groups = MemberNotBlankGroup.class)
    private String memberHp;
    private String memberPw;

    public static MemberPwDTO MemberToMemberPwDTO(Member member){
        MemberPwDTO memberPwDTO = new MemberPwDTO();
        memberPwDTO.setMemberId(member.getMemberId());
        memberPwDTO.setMemberEmail(member.getMemberEmail());
        memberPwDTO.setMemberHp(member.getMemberHp());
        memberPwDTO.setMemberPw(member.getMemberPw());
        return memberPwDTO;
    }
    public static Member MemberPwDTOToMember(MemberPwDTO memberPwDTO){
        Member member = new Member();
        member.setMemberId(memberPwDTO.getMemberId());
        member.setMemberEmail(memberPwDTO.getMemberEmail());
        member.setMemberHp(memberPwDTO.getMemberHp());
        return member;

    }

}
