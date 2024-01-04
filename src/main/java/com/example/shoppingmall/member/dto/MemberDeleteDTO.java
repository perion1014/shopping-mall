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
public class MemberDeleteDTO {
    private Long memberNo;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String memberPw;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String memberPw2;

    public static Member MemberDeleteDTOToMember(Long memberNo,MemberDeleteDTO memberDeleteDTO){
        Member member = new Member();
        member.setMemberNo(memberNo);
        member.setMemberPw(memberDeleteDTO.getMemberPw());
        return member;
    }
}
