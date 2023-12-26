package com.example.shoppingmall.notice.dto;

import com.example.shoppingmall.member.dto.MemberSearchDTO;
import com.example.shoppingmall.notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeSearchDTO {
    private Long noticeNo;
    private String noticeTitle;
    private Timestamp noticeCreatedTime;
    private Long noticeViewcount;

    public static NoticeSearchDTO NoticeToNoticeSearchDTO(Notice notice){
        NoticeSearchDTO noticeSearchDTO = new NoticeSearchDTO();
        noticeSearchDTO.setNoticeNo(notice.getNoticeNo());
        noticeSearchDTO.setNoticeTitle(notice.getNoticeTitle());
        noticeSearchDTO.setNoticeCreatedTime(notice.getNoticeCreatedTime());
        noticeSearchDTO.setNoticeViewcount(notice.getNoticeViewcount());
        return noticeSearchDTO;
    }
}
