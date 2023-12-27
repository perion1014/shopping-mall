package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.*;
import com.example.shoppingmall.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Value("${file.dir}")
    private String fileDir;

    public void saveItem(ItemAddDTO itemAddDTO) {
        //Debugging
        System.out.println("itemService.saveItem(itemAddDTO) ==> item_no = " + itemAddDTO.getItemNo());
        System.out.println("itemService.saveItem(itemAddDTO) ==> item_name = " + itemAddDTO.getItemName());
        Item item = ItemAddDTO.itemAddDTOToItem(itemAddDTO);
        System.out.println("itemService.saveItem(itemAddDTO) ==> item.item_registered_time = " + item.getItemRegisteredTime());
        itemRepository.saveItem(item);
    }

    public Long getMaxItemNo() {
        Long maxItemNo = itemRepository.getMaxItemNo();
        return maxItemNo;
    }

    public void saveItemPhotos(Long itemNo, ItemAddDTO itemAddDTO, MultipartFile itemThumb, MultipartFile itemImg1, MultipartFile itemImg2, MultipartFile itemImg3) throws IOException{
        ItemPhotos itemPhotos = ItemAddDTO.itemAddDTOToItemPhotos(itemNo, itemAddDTO);
        //Debugging
        System.out.println("itemService.saveItemPhotos(itemAddDTO) ==> item_no = " + itemNo);
        System.out.println("itemService.saveItemPhotos(itemAddDTO) ==> item_thumb = " + itemAddDTO.getItemThumb());
        itemRepository.saveItemPhotos(itemPhotos);
        // 파일 처리 (추가)
        String myPath = "D:/intellij/workspace/";
        String fullPath = "";
        //D:/intellij/workspace/shopping-mall/src/main/resources/static/images/itemImages/
        String createdDirPath = myPath + fileDir + itemNo + "/";
        Files.createDirectories(Path.of(createdDirPath));
        Files.createDirectories(Path.of(createdDirPath + "thumb/"));
        if (!itemThumb.isEmpty()) {
            fullPath = createdDirPath + "thumb/" + itemThumb.getOriginalFilename();
            itemThumb.transferTo(new File(fullPath));
        }
        if (!itemImg1.isEmpty()) {
            fullPath = createdDirPath + itemImg1.getOriginalFilename();
            itemImg1.transferTo(new File(fullPath));
        }
        if (!itemImg2.isEmpty()) {
            fullPath = createdDirPath + itemImg2.getOriginalFilename();
            itemImg2.transferTo(new File(fullPath));
        }
        if (!itemImg3.isEmpty()) {
            fullPath = createdDirPath + itemImg3.getOriginalFilename();
            itemImg3.transferTo(new File(fullPath));
        }
    }

    public void saveItemPhotos(Long itemNo, ItemPhotosDTO itemPhotosDTO) {
        //Debugging
        System.out.println("itemService.saveItemPhotos(itemAddDTO) ==> item_no = " + itemNo);
        System.out.println("itemService.saveItemPhotos(itemAddDTO) ==> item_thumb = " + itemPhotosDTO.getItemThumb());
        ItemPhotos itemPhotos = ItemPhotosDTO.toItemPhotos(itemNo, itemPhotosDTO);

        itemRepository.saveItemPhotos(itemPhotos);
    }



    public void saveItemStock(Long itemNo, ItemAddDTO itemAddDTO) {
        //Debugging
        System.out.println("itemService.saveItemStock(itemAddDTO) ==> item_stock_no = " + itemAddDTO.getItemStockNo());
        System.out.println("itemService.saveItemStock(itemAddDTO) ==> item_no = " + itemNo);
        System.out.println("itemService.saveItemStock(itemAddDTO) ==> item_stock_value = " + itemAddDTO.getItemStockValue());
        //ItemStock itemStock = ItemAddDTO.itemAddDTOToItemStock(itemNo, itemAddDTO);
        List<ItemStock> itemStockList = ItemAddDTO.itemAddToItemStockList(itemNo, itemAddDTO);
        for (ItemStock itemStock: itemStockList) {
            itemRepository.saveItemStock(itemStock);
        }
    }

    public List<ItemDTO> findAllItems() {
        List<Item> itemList = itemRepository.findAllItems();
        for (Item item: itemList) {
            Long itemNo = item.getItemNo();
            List<ItemStock> itemStockList = itemRepository.findAllItemStocks(itemNo);
            item.setItemStockList(itemStockList);
        }
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            List<ItemStockDTO> itemStockDTOList = ItemStockDTO.toItemStockDTOList(item.getItemStockList());
            item.setItemStockDTOList(itemStockDTOList);
            itemDTOList.add(ItemDTO.itemToItemDTO(item));
        }
        return itemDTOList;
    }

    public List<ItemDTO> findAllItemsOnsale() {
        List<Item> itemList = itemRepository.findAllItemsOnsale();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.itemToItemDTO(item));
        }
        return itemDTOList;
    }

    public List<ItemDTO> findAllItemsOffmarket() {
        List<Item> itemList = itemRepository.findAllItemsOffmarket();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.itemToItemDTO(item));
        }
        return itemDTOList;
    }

    public ItemDTO findItemByNo(Long itemNo) {
        Item item =  itemRepository.findItemByNo(itemNo);
        return ItemDTO.itemToItemDTO(item);
    }

    public List<ItemDTO> findItemsByName(ItemSearchDTO itemSearchDTO) {
        List<Item> itemList = itemRepository.findItemsByName(itemSearchDTO.getItemName());
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.itemToItemDTO(item));
        }
        return itemDTOList;
    }

    public void updateItemByNo(Long itemNo, ItemUpdateDTO itemUpdateDTO) {
        Item item = ItemUpdateDTO.itemUpdateDTOToItem(itemNo, itemUpdateDTO);
        itemRepository.updateItemByNo(item);
    }

    public void deleteItemStockByItemNo(Long itemNo) {
        itemRepository.deleteItemStockByItemNo(itemNo);
    }

    public void deleteItemPhotosByItemNo(Long itemNo) {
        itemRepository.deleteItemPhotosByItemNo(itemNo);
        // 파일 처리 (삭제)
        String myPath = "D:/intellij/workspace/";
        String createdDirPath = myPath + fileDir + itemNo + "/";
        //D:/intellij/workspace/shopping-mall/src/main/resources/static/images/itemImages/
        try {
            Files.walk(Path.of(createdDirPath))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteItemPyItemNo(Long itemNo) {
        itemRepository.deleteItemByItemNo(itemNo);
    }

    public ItemDTO findItemByNo2(Long itemNo) {
        Item item =  itemRepository.findItemByNo(itemNo);
        return ItemDTO.itemToItemDTO2(item);
    }

    public ItemPhotosDTO findItemPhotosByNo(Long itemNo) {
        ItemPhotos itemPhotos = itemRepository.findItemPhotosByItemNo(itemNo);
        ItemPhotosDTO itemPhotosDTO = ItemPhotosDTO.toItemPhotosDTO(itemPhotos);
        return itemPhotosDTO;
    }

    public List<ItemStockDTO> findItemStockListByNo(Long itemNo) {
        List<ItemStock> itemStockList = itemRepository.findAllItemStocks(itemNo);
        return ItemStockDTO.toItemStockDTOList(itemStockList);
    }

    public String selectItemThumbByItemNo(Long itemNo) {
        return itemRepository.selectItemThumbByItemNo(itemNo);
    }
}
