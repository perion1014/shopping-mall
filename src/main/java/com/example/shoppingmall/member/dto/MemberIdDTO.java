package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static com.example.shoppingmall.member.validation.MemberValidationGroup.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberIdDTO {
    @NotBlank(message = "이메일을 입력해주세요" , groups = MemberNotBlankGroup.class)
    private String memberEmail;
    private String memberId;

    public static MemberIdDTO MemberToMemberIdDTO(Member member){
        MemberIdDTO memberIdDTO = new MemberIdDTO();
        memberIdDTO.setMemberEmail(member.getMemberEmail());
        memberIdDTO.setMemberId(member.getMemberId());
        return memberIdDTO;
    }
    public static Member MemberIdDTOToMember(String memberEmail, MemberIdDTO memberIdDTO){
        Member member = new Member();
        member.setMemberEmail(memberEmail);
        member.setMemberId(memberIdDTO.getMemberId());
        return member;
    }
}
