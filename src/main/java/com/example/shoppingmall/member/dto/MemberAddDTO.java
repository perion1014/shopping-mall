package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static com.example.shoppingmall.member.validation.MemberValidationGroup.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddDTO {

    @NotBlank(message = "아이디를 입력해주세요.", groups = MemberNotBlankGroup.class)
    @Pattern(regexp = "[a-z0-9]{5,20}", message = "잘못된 아이디 형식입니다.", groups = MemberPatternGroup.class)
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
    @NotBlank(message = "이메일을 입력해주세요.", groups = MemberNotBlankGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,50}$", message = "유효하지 않은 이메일 주소입니다.", groups = MemberPatternGroup.class)
    private String memberEmail;
    @NotNull(message = "우편번호를 입력해주세요.", groups = MemberNotBlankGroup.class)
    private Integer memberPostalCode;
    @NotBlank(message = "기본주소를 입력해주세요." ,groups = MemberNotBlankGroup.class)
    private String memberAddressBasic;
    @Pattern(regexp = "[가-힣]{0,50}", message = "최대 50자까지 가능합니다.", groups = MemberPatternGroup.class)
    private String memberAddressDetail;

    public static Member MemberAddDTOToMember(MemberAddDTO memberAddDTO) {
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
