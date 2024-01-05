package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.validation.MemberValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.shoppingmall.member.validation.MemberValidationGroup.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
    private Long memberNo;
    private String memberEmail;
    private String memberId;
    @NotBlank(message = "비밀번호를 입력해주세요.", groups = MemberNotBlankGroup.class)
    @Size( min = 8, max = 20 , groups = MemberPatternGroup.class, message="잘못된 비밀번호 형식입니다.")
    private String memberPw;
    @NotBlank(message = "비밀번호를 입력해주세요.", groups = MemberNotBlankGroup.class)
    @Size( min = 8, max = 20 , groups = MemberPatternGroup.class, message="잘못된 비밀번호 형식입니다.")
    private String memberPw2;
    @NotBlank(message = "휴대폰 번호를 입력해주세요.", groups = MemberNotBlankGroup.class)
    @Pattern(regexp = "^01(?:0|1|[6-9])\\d{7,8}$", message = "유효하지 않은 휴대폰 번호입니다.", groups = MemberPatternGroup.class)
    private String memberHp;
    @NotBlank(message = "성명을 입력해주세요.", groups = MemberNotBlankGroup.class)
    @Pattern(regexp = "[가-힣]{2,40}", message = "잘못된 이름 형식입니다.", groups = MemberPatternGroup.class)
    private String memberName;
    @NotNull(message = "우편번호를 입력해주세요.", groups = MemberNotBlankGroup.class)
    private Integer memberPostalCode;
    @NotBlank(message = "기본번호를 입력해주세요." ,groups = MemberNotBlankGroup.class)
    private String memberAddressBasic;
    @Pattern(regexp = "[가-힣]{0,50}", message = "최대 50자까지 가능합니다.", groups = MemberPatternGroup.class)
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
