package com.example.shoppingmall.notify.dto;

import com.example.shoppingmall.notify.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private int noticeNo;
    private String noticeTitle;
    private Timestamp noticeCreateTime;
    private String noticeContent;
    private int viewCount;

    public static Notice noticeDTOToNotice(NoticeDTO noticeDTO){
        Notice notice = new Notice();
        notice.setNoticeNo(noticeDTO.getNoticeNo());
        notice.setNoticeTitle(noticeDTO.getNoticeTitle());
        notice.setNoticeCreateTime(notice.getNoticeCreateTime());
        notice.setNoticeContent(noticeDTO.getNoticeContent());
        notice.setViewCount(notice.getViewCount());
        return notice;
    }


}
