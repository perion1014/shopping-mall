package com.example.shoppingmall.notify.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private Long noticeNo;
    private Integer adminNo;
    private String noticeTitle;
    private Timestamp noticeCreatedTime;
    private String noticeContent;
    private Long noticeViewcount;
}
