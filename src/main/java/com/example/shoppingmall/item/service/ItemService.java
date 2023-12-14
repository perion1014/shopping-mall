package com.example.shoppingmall.item.service;

import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {
//    public List<ItemDTO> getAllItems() {
//        List<ItemEntity> itemEntityList = itemRepository.findAllItems();
//        List<ItemDTO> itemDTOList = new ArrayList<>();
//        for (ItemEntity itemEntity: itemEntityList) {
//            itemEntityList.add(ItemDTO.toItemDTO(itemEntity));
//        }
//        return itemDTOList;
//    }

    List<ItemDTO> getAllItems();
}

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemRepository itemRepository;

}
