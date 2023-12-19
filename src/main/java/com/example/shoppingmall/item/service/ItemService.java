package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.domain.Item;
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
        Item item = ItemAddDTO.itemAddDTOToItem(itemAddDTO);
        itemRepository.saveItem(item);
    }

    public void saveItemStock(ItemStockAddDTO itemStockAddDTO) {
        ItemStock itemStock = ItemStockAddDTO.toItemStock(itemStockAddDTO);
        itemRepository.saveItemStock(itemStock);
    }

    public List<ItemAddDTO> findAllItems() {
        List<Item> itemList = itemRepository.findAllItems();
        List<ItemAddDTO> itemAddDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemAddDTOList.add(ItemAddDTO.itemToItemAddDTO(item));
        }
        return itemAddDTOList;
    }

    public List<ItemDTO> findAllItemsOnsale() {
        List<Item> itemList = itemRepository.findAllItemsOnsale();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.ItemtoItemDTO(item));
        }
        return itemDTOList;
    }

    public List<ItemDTO> findAllItemsOffmarket() {
        List<Item> itemList = itemRepository.findAllItemsOffmarket();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.ItemtoItemDTO(item));
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
        return ItemDTO.ItemtoItemDTO(item);
    }

    public List<ItemDTO> findItemsByName(ItemSearchDTO itemSearchDTO) {
        List<Item> itemList = itemRepository.findItemsByName(itemSearchDTO.getItemName());
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.ItemtoItemDTO(item));
        }
        return itemDTOList;
    }

    public void updateItemByNo(Long itemNo, ItemUpdateDTO itemUpdateDTO) {
        Item item = ItemUpdateDTO.itemUpdateDTOToItem(itemUpdateDTO);
        itemRepository.updateItemByNo(itemNo, item);
    }

    public void deleteItemByNo(Long itemNo) {
        itemRepository.deleteItemByNo(itemNo);
    }

    public void deleteItemByName(ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        itemRepository.deleteItemByName(item);
    }


}
