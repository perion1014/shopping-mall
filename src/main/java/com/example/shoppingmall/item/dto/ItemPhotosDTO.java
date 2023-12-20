package com.example.shoppingmall.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPhotosDTO {

    private Long itemNo;
    private String itemThumb;
    private String itemImg1;
    private String itemImg2;
    private String itemImg3;
    private String itemThumbModified;
    private String itemImg1Modified;
    private String itemImg2Modified;
    private String itemImg3Modified;

}
