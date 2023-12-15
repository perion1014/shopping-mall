package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.domain.Item;
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

    public List<ItemDTO> getAllItems() {
        List<Item> itemList = itemRepository.getAllItems();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.ItemtoItemDTO(item));
        }
        return itemDTOList;
    }

    public void saveItem(ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        itemRepository.saveItem(item);
    }

    public ItemDTO findItemByName(ItemDTO itemDTO) {
        Item item = itemRepository.findByItemName(Item.itemDTOToItemWithItemNo(itemDTO));
        return ItemDTO.ItemtoItemDTO(item);
    }

    public void deleteItemById(ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        itemRepository.deleteItemById(item);
    }

    public void deleteItemByName(ItemDTO itemDTO) {
        Item item = Item.itemDTOToItemWithItemNo(itemDTO);
        itemRepository.deleteItemByName(item);
    }
}
