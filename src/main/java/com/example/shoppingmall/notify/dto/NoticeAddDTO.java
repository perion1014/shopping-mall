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

    public static  NoticeAddDTO noticeToNoticeAddDTO(Notice notice){
        NoticeAddDTO noticeAddDTO = new NoticeAddDTO();
        noticeAddDTO.setNoticeContent(notice.getNoticeContent());
        noticeAddDTO.setNoticeTitle(notice.getNoticeTitle());
        return noticeAddDTO;
    }
    public static Notice noticeAddDTOToNotice(NoticeAddDTO noticeAddDTO){
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeAddDTO.getNoticeTitle());
        notice.setNoticeContent(noticeAddDTO.getNoticeContent());
        return notice;
    }
}
