package com.example.shoppingmall.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemFile {

    private Long itemNo;

    /* 파일 이름 */
    private String fileName;

    /* 경로 */
    private String uploadPath;

    /* uuid */
    private String uuid;


}
