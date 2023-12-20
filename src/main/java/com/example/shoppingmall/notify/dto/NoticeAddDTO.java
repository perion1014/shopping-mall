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
public class NoticeAddDTO {
    private String noticeTitle;
    private String noticeContent;


    public static Notice NoticeAddDTOToNotice(Long adminNo,NoticeAddDTO noticeAddDTO){
        Notice notice = new Notice();
        notice.setAdminNo(adminNo);
        notice.setNoticeTitle(noticeAddDTO.getNoticeTitle());
        notice.setNoticeContent(noticeAddDTO.getNoticeContent());
        return notice;
    }
}
