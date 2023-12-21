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
public class NoticeUpdateDTO {

    private String noticeTitle;
    private String noticeContent;

    public static Notice noticeUpdateDTOToNotice(Long noticeNo,NoticeUpdateDTO noticeUpdateDTO){
        Notice notice = new Notice();
        notice.setNoticeNo(notice.getNoticeNo());
        notice.setNoticeTitle(noticeUpdateDTO.getNoticeTitle());
        notice.setNoticeContent(noticeUpdateDTO.getNoticeContent());
        return notice;

    }

}
