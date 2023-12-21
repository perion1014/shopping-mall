package com.example.shoppingmall.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPageDTO {
    private int page; // 현재 페이지
    private int maxPage; // 전체 필요한 페이지 갯수
    private int startPage; // 현제 페이지 기준 시작 페이지 값
    private int endPage; // 현제 페이지 기준 시작 페이지 값
}
