package com.example.shoppingmall.item.dto;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    // ItemPhotos Fields
    private MultipartFile itemThumb;
    private MultipartFile itemImg1;
    private MultipartFile itemImg2;
    private MultipartFile itemImg3;
    private String itemThumbModified;
    private String itemImg1Modified;
    private String itemImg2Modified;
    private String itemImg3Modified;

    // ItemStock Fields
    private Long itemStockNo;
    private String itemSize;
    private Integer itemStockValue;

    private String itemSize1;
    private String itemSize2;
    private String itemSize3;
    private Integer itemStockValue1;
    private Integer itemStockValue2;
    private Integer itemStockValue3;

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
        return item;
    }

    public static ItemPhotos itemAddDTOToItemPhotos(Long itemNo, ItemAddDTO itemAddDTO) {
        ItemPhotos itemPhotos = new ItemPhotos();
        itemPhotos.setItemNo(itemNo);
        itemPhotos.setItemThumb(itemAddDTO.getItemThumb().getOriginalFilename());
        itemPhotos.setItemImg1(itemAddDTO.getItemImg1().getOriginalFilename());
        itemPhotos.setItemImg2(itemAddDTO.getItemImg2().getOriginalFilename());
        itemPhotos.setItemImg3(itemAddDTO.getItemImg3().getOriginalFilename());
        itemPhotos.setItemThumbModified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemThumbModified()));
        itemPhotos.setItemImg1Modified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemImg1Modified()));
        itemPhotos.setItemImg2Modified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemImg2Modified()));
        itemPhotos.setItemImg3Modified(ItemAddDTO.generateRandomFileName(itemAddDTO.getItemImg3Modified()));
        return itemPhotos;
    }

    public static ItemStock itemAddDTOToItemStock1(Long itemNo, ItemAddDTO itemAddDTO) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemStockNo(itemAddDTO.getItemStockNo());
        itemStock.setItemNo(itemNo);
        itemStock.setItemSize(itemAddDTO.getItemSize1());
        itemStock.setItemStockValue(itemAddDTO.getItemStockValue1());
        return itemStock;
    }

    public static ItemStock itemAddDTOToItemStock2(Long itemNo, ItemAddDTO itemAddDTO) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemStockNo(itemAddDTO.getItemStockNo());
        itemStock.setItemNo(itemNo);
        itemStock.setItemSize(itemAddDTO.getItemSize2());
        itemStock.setItemStockValue(itemAddDTO.getItemStockValue2());
        return itemStock;
    }

    public static ItemStock itemAddDTOToItemStock3(Long itemNo, ItemAddDTO itemAddDTO) {
        ItemStock itemStock = new ItemStock();
        itemStock.setItemStockNo(itemAddDTO.getItemStockNo());
        itemStock.setItemNo(itemNo);
        itemStock.setItemSize(itemAddDTO.getItemSize3());
        itemStock.setItemStockValue(itemAddDTO.getItemStockValue3());
        return itemStock;
    }

    public static List<ItemStock> itemAddToItemStockList(Long itemNo, ItemAddDTO itemAddDTO) {
        List<ItemStock> itemStockList = new ArrayList<>();
        itemStockList.add(ItemAddDTO.itemAddDTOToItemStock1(itemNo, itemAddDTO));
        itemStockList.add(ItemAddDTO.itemAddDTOToItemStock2(itemNo, itemAddDTO));
        itemStockList.add(ItemAddDTO.itemAddDTOToItemStock3(itemNo, itemAddDTO));
        return itemStockList;
    }

    public static ItemAddDTO itemToItemAddDTO(Item item) {
        ItemAddDTO itemAddDTO = new ItemAddDTO();
        itemAddDTO.setItemNo(item.getItemNo());
        itemAddDTO.setItemName(item.getItemName());
        itemAddDTO.setItemCategory(item.getItemCategory());
        itemAddDTO.setItemPrice(item.getItemPrice());
        itemAddDTO.setItemPrice((item.getItemOnsale()));
        itemAddDTO.setItemDetail(item.getItemDetail());
        return itemAddDTO;
    }

}
