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
public class NoticeListDTO {
    private Integer noticeNo;
    private String noticeTitle;
    private String noticeCreateTime;
    private Integer viewCount;

    public static NoticeListDTO NoticeToNoticeListDTO(Notice notice){
        NoticeListDTO noticeListDTO = new NoticeListDTO();
        noticeListDTO.setNoticeNo(notice.getNoticeNo());
        noticeListDTO.setNoticeTitle(notice.getNoticeTitle());
        noticeListDTO.setNoticeCreateTime(notice.getNoticeCreateTime());
        noticeListDTO.setViewCount(notice.getViewCount());
        return noticeListDTO;
    }

}
