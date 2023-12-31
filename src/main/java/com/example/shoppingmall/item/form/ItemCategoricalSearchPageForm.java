package com.example.shoppingmall.item.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoricalSearchPageForm {
    private int page;
    private int itemsPerPage;
    private int totalPage; // 전체 필요한 페이지 갯수
    private int startPage; // 현재 페이지 기준 시작 페이지 값
    private int endPage; // 현재 페이지 기준 시작 페이지 값
    private String searchKeyword;
    private String category;
}
