package com.example.shoppingmall.item.domain;

import com.example.shoppingmall.item.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

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

    // 1:1
    private ItemPhotos itemPhotos;
    private ItemPhotosDTO itemPhotosDTO;

    // 1:N
    private List<ItemStock> itemStockList;
    private List<ItemStockDTO> itemStockDTOList;

}
