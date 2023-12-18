package com.example.shoppingmall.cart.service;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    public static String getItemThumbnail(Long itemNo){
    String itemThumbnail = "";

    return itemThumbnail;
    }

    public static String getItemName(){
        String itemName = "";

        return itemName;
    }

    public static String getItemSize(){
        String itemSize = "";

        return itemSize;
    }

    public static Integer getItemPrice(){
        Integer itemPrice = 0;

        return itemPrice;
    }

    public static Integer getItemQuantity(){
        Integer itemQuantity = 0;

        return itemQuantity;
    }

    public static Integer getItemPriceSum(){
        Integer itemPriceSum = 0;

        return itemPriceSum;
    }

    public static Integer getOrderPriceSum(){
        Integer orderPriceSum = 0;

        return orderPriceSum;
    }

   // cartReadDTO.setItemName(CartService.getItemName());           // item에서 가져옴
   // cartReadDTO.setItemSize(CartService.getItemSize());           // cart에서 가져옴
   // cartReadDTO.setItemPrice(CartService.getItemPrice());         // item에서 가져옴
   // cartReadDTO.setItemQuantity(CartService.getItemQuantity());   // cart에서 가져옴
   // cartReadDTO.setItemPriceSum(CartService.getItemPriceSum());   // itemPrice로 계산
   // cartReadDTO.setOrderPriceSum(CartService.getOrderPriceSum()); // otemPriceSum으로 계산
}
