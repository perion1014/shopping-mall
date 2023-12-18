package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.domain.Item;
import com.example.shoppingmall.item.dto.ItemAddDTO;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.dto.ItemSearchDTO;
import com.example.shoppingmall.item.dto.ItemUpdateDTO;
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

    public ItemDTO findItemByNo(Long itemNo) {
        Item item =  itemRepository.findItemByNo(itemNo);
        return ItemDTO.ItemtoItemDTO(item);
    }

    public List<ItemDTO> findItemsByName(ItemSearchDTO itemSearchDTO) {
        List<Item> itemList = itemRepository.findItemsByName(ItemSearchDTO.itemSearchDTOToItem(itemSearchDTO));
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
