package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.domain.ItemPhotos;
import com.example.shoppingmall.item.domain.ItemStock;
import com.example.shoppingmall.item.dto.*;
import com.example.shoppingmall.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

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

    public void saveItemPhotos(Long itemNo, ItemAddDTO itemAddDTO) {
        //Debugging
        System.out.println("itemService.saveItemPhotos(itemAddDTO) ==> item_no = " + itemNo);
        System.out.println("itemService.saveItemPhotos(itemAddDTO) ==> item_thumb = " + itemAddDTO.getItemThumb());
        ItemPhotos itemPhotos = ItemAddDTO.itemAddDTOToItemPhotos(itemNo, itemAddDTO);
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
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            /*
            구현 필요
             */
            Long itemNo = item.getItemNo();
            item.setItemStockList(itemRepository.findAllItemStocks(itemNo));
            itemDTOList.add(ItemDTO.itemToItemDTO(item));
        }
        return itemDTOList;
    }

    public List<ItemStockDTO> findAllItemStocks(Long itemNo) {
        List<ItemStock> itemStockList = itemRepository.findAllItemStocks(itemNo);
        List<ItemStockDTO> itemStockDTOList = new ArrayList<>();
        return itemStockDTOList;
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

    public List<ItemStockDTO> joinItemByItemNo(Long itemNo) {
        List<ItemStock> itemStockList = itemRepository.joinItemByItemNo(itemNo);
        List<ItemStockDTO> itemStockDTOList = new ArrayList<>();
        for (ItemStock itemStock: itemStockList) {
            itemStockDTOList.add(ItemStockDTO.toItemStock(itemStock));
        }
        return itemStockDTOList;
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

    public void deleteItemByNo(Long itemNo, ItemDeleteDTO itemDeleteDTO) {
        Item item = ItemDeleteDTO.toItem(itemNo, itemDeleteDTO);
        ItemPhotos itemPhotos = ItemDeleteDTO.toItemPhotos(itemNo, itemDeleteDTO);
        List<ItemStock> itemStockList = itemRepository.findItemStocksByItemNo(itemNo);
        itemRepository.deleteItemByNo(item);
        itemRepository.deleteItemPhotosByNo(itemPhotos);
        for (ItemStock itemStock: itemStockList) {
            itemRepository.deleteItemStockByStockNo(itemStock);
        }
    }

    public void deleteItemByName(ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        itemRepository.deleteItemByName(item);
    }


}
