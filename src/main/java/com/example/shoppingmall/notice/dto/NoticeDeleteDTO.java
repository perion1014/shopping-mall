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
public class NoticeDeleteDTO {

    private Long noticeNo;

    public static Notice NoticeDeleteDTOToNotice(Long noticeNo, NoticeDeleteDTO noticeDeleteDTO){
        Notice notice = new Notice();
        notice.setNoticeNo(noticeNo);
        return notice;
    }

}
