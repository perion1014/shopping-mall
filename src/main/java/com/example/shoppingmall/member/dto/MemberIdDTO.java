package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberIdDTO {
    @NotBlank(message = "이메일을 입력해주세요")
    private String memberEmail;

    public static MemberIdDTO MemberToMemberIdDTO(Member member){
        MemberIdDTO memberIdDTO = new MemberIdDTO();
        memberIdDTO.setMemberEmail(member.getMemberEmail());
        return memberIdDTO;
    }
}
