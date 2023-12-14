package com.example.shoppingmall.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long itemNo;
    private String itemName;
    private String itemCategory;
    private Double itemGrade;
    private String itemDetail;
    private Integer itemPrice;
    private Integer itemOnsale;
    private Timestamp itemRegisteredTime;
}
