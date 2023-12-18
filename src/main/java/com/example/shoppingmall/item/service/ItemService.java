package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.dto.ItemAddDTO;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public boolean saveItem(ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        return itemRepository.saveItem(item);
    }

    public List<ItemAddDTO> findAllItems() {
        List<Item> itemList = itemRepository.findAllItems();
        List<ItemAddDTO> itemAddDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemAddDTOList.add(Item.itemToItemAddDTO(item));
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

    public ItemDTO findItemByNo(Long itemNo) {
        Item item =  itemRepository.findItemByNo(itemNo);
        return ItemDTO.ItemtoItemDTO(item);
    }

    public List<ItemDTO> findItemsByName(ItemDTO itemDTO) {
        List<Item> itemList = itemRepository.findItemsByName(Item.itemDTOToItemWithItemNo(itemDTO));
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.ItemtoItemDTO(item));
        }
        return itemDTOList;
    }

    public boolean updateItemByNo(Long itemNo, ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        return itemRepository.updateItemByNo(itemNo, item);
    }

    public void deleteItemByNo(Long itemNo) {
        itemRepository.deleteItemByNo(itemNo);
    }

    public boolean deleteItemByName(ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        return itemRepository.deleteItemByName(item);
    }


}
