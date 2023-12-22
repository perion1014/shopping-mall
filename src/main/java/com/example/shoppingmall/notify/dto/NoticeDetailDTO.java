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
public class NoticeDetailDTO {
    private Long noticeNo;
    private String noticeTitle;
    private String noticeContent;
//    private String noticeCreatedTime;
    private Long noticeViewCount;

    public static Notice noticeDetailDTOToNotice(Long noticeNo, NoticeDetailDTO noticeDetailDTO){
        Notice notice = new Notice();
        notice.setNoticeNo(notice.getNoticeNo());
        notice.setNoticeTitle(noticeDetailDTO.getNoticeTitle());
        notice.setNoticeContent(noticeDetailDTO.getNoticeContent());
        notice.setNoticeViewcount(noticeDetailDTO.getNoticeViewCount());
        return notice;
    }

    public static NoticeDetailDTO noticeToNoticeDetailDTO(Notice notice){
        NoticeDetailDTO noticeDetailDTO = new NoticeDetailDTO();
//        noticeDetailDTO.setNoticeNo();
        return noticeDetailDTO;
    }

}
