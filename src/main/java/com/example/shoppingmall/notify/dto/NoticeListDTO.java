package com.example.shoppingmall.notify.dto;

import com.example.shoppingmall.notify.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListDTO {
    private int noticeNo;
    private String noticeTitle;
    private String noticeCreateTime;
    private String noticeContent;
    private int viewCount;

    public static Notice noticeDTOToNotice(NoticeListDTO noticeListDTO){
        Notice notice = new Notice();
        notice.setNoticeNo(noticeListDTO.getNoticeNo());
        notice.setNoticeTitle(noticeListDTO.getNoticeTitle());
        notice.setNoticeCreateTime(notice.getNoticeCreateTime());
        notice.setNoticeContent(noticeListDTO.getNoticeContent());
        notice.setViewCount(notice.getViewCount());
        return notice;
    }
/*public static MemberListDTO MemberToMemberListDTO(Member member){
        MemberListDTO memberListDTO = new MemberListDTO();
        memberListDTO.setMemberNo(member.getMemberNo());
        memberListDTO.setMemberId(member.getMemberId());
        memberListDTO.setMemberName(member.getMemberName());
        memberListDTO.setMemberHp(member.getMemberHp());
        memberListDTO.setMemberEmail(memberListDTO.getMemberEmail());
        memberListDTO.setMemberPostalCode(memberListDTO.getMemberPostalCode());
        memberListDTO.setMemberAddressBasic(memberListDTO.getMemberAddressBasic());
        memberListDTO.setMemberAddressDetail(memberListDTO.getMemberAddressDetail());
        return memberListDTO;
    }*/

}
