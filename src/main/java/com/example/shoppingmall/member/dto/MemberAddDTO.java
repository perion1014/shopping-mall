package com.example.shoppingmall.member.dto;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.dto.MemberOrderDetailAddDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddDTO {

    private String memberId;
    private String memberPw;
    private String memberPw2;
    private String memberHp;
    private String memberName;
    private String memberEmail;
    private Integer memberAddCode;
    private Integer memberPostalCode;
    private String memberAddressBasic;
    private String memberAddressDetail;

    private List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList;


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
        member.setMemberOrderDetailList(MemberAddDTO.toMemberOrderDetailList(memberAddDTO.getMemberOrderDetailAddDTOList()));
        return member;
    }

    public static List<MemberOrderDetail> toMemberOrderDetailList(List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList) {
        List<MemberOrderDetail> memberOrderDetailList = new ArrayList<>();
        for (MemberOrderDetailAddDTO memberOrderDetailAddDTO: memberOrderDetailAddDTOList) {
            MemberOrderDetail memberOrderDetail = new MemberOrderDetail();
            memberOrderDetail.setMemberOrderDetailNo(memberOrderDetailAddDTO.getMemberOrderDetailNo());
            memberOrderDetail.setMemberOrderNo(memberOrderDetailAddDTO.getMemberOrderNo());
            memberOrderDetail.setItemNo(memberOrderDetailAddDTO.getItemNo());
            memberOrderDetail.setItemQuantity(memberOrderDetailAddDTO.getItemQuantity());
            memberOrderDetail.setItemSize(memberOrderDetailAddDTO.getItemSize());
            memberOrderDetailList.add(memberOrderDetail);
        }
        return memberOrderDetailList;
    }

    public static List<MemberOrderDetailAddDTO> toMemberOrderDetailAddDTOList(List<MemberOrderDetail> memberOrderDetailList) {
        List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList = new ArrayList<>();
        for (MemberOrderDetail memberOrderDetail: memberOrderDetailList) {
            MemberOrderDetailAddDTO memberOrderDetailAddDTO = new MemberOrderDetailAddDTO();
            memberOrderDetailAddDTO.setMemberOrderDetailNo(memberOrderDetail.getMemberOrderDetailNo());
            memberOrderDetailAddDTO.setMemberOrderNo(memberOrderDetail.getMemberOrderNo());
            memberOrderDetailAddDTO.setItemNo(memberOrderDetail.getItemNo());
            memberOrderDetailAddDTO.setItemQuantity(memberOrderDetail.getItemQuantity());
            memberOrderDetailAddDTO.setItemSize(memberOrderDetail.getItemSize());
            memberOrderDetailAddDTOList.add(memberOrderDetailAddDTO);
        }
        return memberOrderDetailAddDTOList;
    }
}
