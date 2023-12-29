package com.example.shoppingmall.member.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchForm {
    private String category;
    private String keyword;
    private Integer startPage;
    private Integer memberPerPage;
}

