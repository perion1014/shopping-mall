package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemAddDTO {

    // Item Fields
    private Long itemNo;
    private String itemName;
    private String itemCategory;
    private Integer itemPrice;
    private Integer itemOnsale;
    private String itemDetail;
    private Timestamp itemRegisteredTime;

    // ItemStock Fields
    private Long itemStockNo;
    private String itemSize;
    private Integer itemStock;

    // ItemPhotos Fields
    private String itemThumb;
    private String itemImg1;
    private String itemImg2;
    private String itemImg3;
    private String itemThumbModified;
    private String itemImg1Modified;
    private String itemImg2Modified;
    private String itemImg3Modified;

    public static String generateRandomFileName(String originalFilename) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static Item itemAddDTOToItem(ItemAddDTO itemAddDTO) {
        Item item = new Item();
        item.setItemNo(itemAddDTO.getItemNo());
        item.setItemName(itemAddDTO.getItemName());
        item.setItemCategory(itemAddDTO.getItemCategory());
        item.setItemPrice(itemAddDTO.getItemPrice());
        item.setItemOnsale(itemAddDTO.getItemOnsale());
        item.setItemDetail(itemAddDTO.getItemDetail());
        item.setItemRegisteredTime(itemAddDTO.getItemRegisteredTime());
        return item;
    }

    public static ItemPhotos itemAddDTOToItemPhotos(ItemAddDTO itemAddDTO) {
        ItemPhotos itemPhotos = new ItemPhotos();
        itemPhotos.setItemNo(itemAddDTO.getItemNo());
        itemPhotos.setItemThumb(itemAddDTO.getItemThumb());
        itemPhotos.setItemImg1(itemAddDTO.getItemImg1());
        itemPhotos.setItemImg2(itemAddDTO.getItemImg2());
        itemPhotos.setItemImg3(itemAddDTO.getItemImg3());
//        itemPhotos.setItemThumbModified(itemAddDTO.getItemThumbModified());
//        itemPhotos.setItemImg1Modified(itemAddDTO.getItemImg1Modified());
//        itemPhotos.setItemImg2Modified(itemAddDTO.getItemImg2Modified());
//        itemPhotos.setItemImg3Modified(itemAddDTO.getItemImg3Modified());
        itemPhotos.setItemThumbModified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemThumbModified()));
        itemPhotos.setItemImg1Modified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemImg1Modified()));
        itemPhotos.setItemImg2Modified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemImg2Modified()));
        itemPhotos.setItemImg3Modified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemImg3Modified()));
        return itemPhotos;
    }

    public static ItemStock itemAddDTOToItemStock(ItemAddDTO itemAddDTO) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemStockNo(itemAddDTO.getItemStockNo());
        itemStock.setItemNo(itemAddDTO.getItemNo());
        itemStock.setItemSize(itemAddDTO.getItemSize());
        itemStock.setItemStock(itemAddDTO.getItemStock());
        return itemStock;
    }

    public static ItemAddDTO itemToItemAddDTO(Item item) {
        ItemAddDTO itemAddDTO = new ItemAddDTO();
        itemAddDTO.setItemNo(item.getItemNo());
        itemAddDTO.setItemName(item.getItemName());
        itemAddDTO.setItemCategory(item.getItemCategory());
        itemAddDTO.setItemPrice(item.getItemPrice());
        itemAddDTO.setItemPrice((item.getItemOnsale()));
        itemAddDTO.setItemDetail(item.getItemDetail());
        itemAddDTO.setItemRegisteredTime(item.getItemRegisteredTime());
        return itemAddDTO;
    }

}
