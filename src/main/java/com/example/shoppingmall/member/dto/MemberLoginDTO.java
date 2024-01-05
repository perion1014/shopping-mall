package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.validation.MemberValidationGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.shoppingmall.member.validation.MemberValidationGroup.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDTO {


    @NotBlank(message="아이디를 입력해주세요." ,groups = MemberNotBlankGroup.class)
    private String memberId;
    @NotBlank(message="비밀번호를 입력해주세요.",groups = MemberNotBlankGroup.class)
    private String memberPw;

    public static Member MemberLoginDTOToMember(MemberLoginDTO memberLoginDTO){
        Member member = new Member();
        member.setMemberId(memberLoginDTO.getMemberId());
        member.setMemberPw(memberLoginDTO.getMemberPw());
        return member;
    }
}
