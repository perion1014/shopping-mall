package com.example.shoppingmall.notice.dto;

import com.example.shoppingmall.notice.domain.Notice;
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


    public static Notice NoticeAddDTOToNotice(Integer adminNo,NoticeAddDTO noticeAddDTO){
        Notice notice = new Notice();
        notice.setNoticeNo(notice.getNoticeNo());
        notice.setAdminNo(adminNo);
        System.out.println("addDTOadminNo = " + adminNo);
        notice.setNoticeTitle(noticeAddDTO.getNoticeTitle());
        notice.setNoticeContent(noticeAddDTO.getNoticeContent());
        return notice;
    }
}
