package com.example.shoppingmall.notice.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeSearchForm {
    private String category;
    private String keyword;
    private Integer startPage;
    private Integer pagePerNotice;
}
