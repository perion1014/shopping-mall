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
public class NoticeUpdateDTO {
    private Long noticeNo;
    private String noticeTitle;
    private String noticeContent;
    //private Timestamp noticeCreatedTime;//수정시간이 notice-modify.html로 전달해야함
    private Long noticeViewcount;

    public static Notice NoticeUpdateDTOToNotice(NoticeUpdateDTO noticeUpdateDTO){
        Notice notice = new Notice();
//        notice.setAdminNo(adminNo);
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
        //수정시간이 들어가야됨
        noticeUpdateDTO.setNoticeViewcount(notice.getNoticeViewcount());
        return noticeUpdateDTO;
    }


}
