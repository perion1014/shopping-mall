package com.example.shoppingmall.notice.dto;

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
public class NoticeUpdateDTO {
    private Long noticeNo;
    private String noticeTitle;
    private String noticeContent;
    private Timestamp noticeCreatedTime;
    private Long noticeViewcount;

    public static Notice NoticeUpdateDTOToNotice(NoticeUpdateDTO noticeUpdateDTO){
        Notice notice = new Notice();
        notice.setNoticeNo(noticeUpdateDTO.getNoticeNo());
        notice.setNoticeTitle(noticeUpdateDTO.getNoticeTitle());
        notice.setNoticeContent(noticeUpdateDTO.getNoticeContent());
        notice.setNoticeViewcount(noticeUpdateDTO.getNoticeViewcount());
        return notice;

    }
    public static NoticeUpdateDTO NoticeToNoticeUpdateDTO(Notice notice){
        NoticeUpdateDTO noticeUpdateDTO = new NoticeUpdateDTO();
        noticeUpdateDTO.setNoticeNo(notice.getNoticeNo());
        noticeUpdateDTO.setNoticeTitle(notice.getNoticeTitle());
        noticeUpdateDTO.setNoticeContent(notice.getNoticeContent());
        noticeUpdateDTO.setNoticeCreatedTime(notice.getNoticeCreatedTime());
        noticeUpdateDTO.setNoticeViewcount(notice.getNoticeViewcount());
        return noticeUpdateDTO;
    }


}
