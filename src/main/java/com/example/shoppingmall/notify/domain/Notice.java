package com.example.shoppingmall.notify.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private Integer noticeNo;
    private Integer adminNo;
    private String noticeTitle;
    private String noticeCreateTime;
    private String noticeContent;
    private Integer viewCount;
}
