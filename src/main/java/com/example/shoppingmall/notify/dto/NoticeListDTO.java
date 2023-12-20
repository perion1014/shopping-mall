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
public class NoticeListDTO {
    private Long noticeNo;
    private String noticeTitle;
    private Timestamp noticeCreatedTime;
    private Long noticeViewcount;

    public static NoticeListDTO NoticeToNoticeListDTO(Notice notice){
        NoticeListDTO noticeListDTO = new NoticeListDTO();
        noticeListDTO.setNoticeNo(notice.getNoticeNo());
        noticeListDTO.setNoticeTitle(notice.getNoticeTitle());
        noticeListDTO.setNoticeCreatedTime(notice.getNoticeCreatedTime());
        noticeListDTO.setNoticeViewcount(notice.getNoticeViewcount());
        return noticeListDTO;
    }

}
