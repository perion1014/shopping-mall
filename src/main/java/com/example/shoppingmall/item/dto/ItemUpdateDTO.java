package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateDTO {
    private Long itemNo;
    private String itemName;
    private String itemCategory;
    private Double itemGrade;
    private String itemDetail;
    private Integer itemPrice;
    private Integer itemOnsale;

    private List<ItemStockDTO> itemStockDTOList;


    public static Item itemUpdateDTOToItem(Long itemNo, ItemUpdateDTO itemUpdateDTO) {
        Item item = new Item();
        item.setItemNo(itemNo);
        item.setItemName(itemUpdateDTO.getItemName());
        item.setItemCategory(itemUpdateDTO.getItemCategory());
        item.setItemGrade(itemUpdateDTO.getItemGrade());
        item.setItemDetail(itemUpdateDTO.getItemDetail());
        item.setItemPrice(itemUpdateDTO.getItemPrice());
        item.setItemOnsale(itemUpdateDTO.getItemOnsale());
        item.setItemStockList(ItemUpdateDTO.toItemStockList(itemNo, itemUpdateDTO.getItemStockDTOList()));
        return item;
    }

    public static List<ItemStock> toItemStockList(Long itemNo, List<ItemStockDTO> itemStockDTOList) {
        List<ItemStock> itemStockList = new ArrayList<>();
        for (ItemStockDTO itemStockDTO: itemStockDTOList) {
            ItemStock itemStock = new ItemStock();
            itemStock.setItemStockNo(itemStockDTO.getItemStockNo());
            itemStock.setItemNo(itemNo);
            itemStock.setItemSize(itemStockDTO.getItemSize());
            itemStock.setItemStockValue(itemStockDTO.getItemStockValue());
            itemStockList.add(itemStock);
        }
        return itemStockList;
    }



}
