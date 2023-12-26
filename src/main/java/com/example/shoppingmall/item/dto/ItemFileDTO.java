package com.example.shoppingmall.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemFileDTO {

    private Long itemNo;
    private String filename;
    private String filepath;

}
