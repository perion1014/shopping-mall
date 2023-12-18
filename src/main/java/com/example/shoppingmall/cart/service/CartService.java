package com.example.shoppingmall.cart.service;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public ArrayList<CartReadDTO> getCartList(Long memberNo){
        System.out.println("Service단 진입");
        ArrayList<Cart> dbCartList = cartRepository.getCartList(memberNo);
        System.out.println("장바구니 조회 쿼리로 값을 받아왔나요? : " + dbCartList.isEmpty());
        //ArrayList<CartReadDTO> readCart = new ArrayList<CartReadDTO>();
        //for(Cart cart: dbCartList ){
        //    readCart.add(CartReadDTO.CartToCartDTO(cart));
        //}
        //return readCart;
        return null;
    }

    public static String getItemThumbnail(Long itemNo){
    String itemThumbnail = "";

    return itemThumbnail;
    }

    public static String getItemName(Long itemNo){
        String itemName = "";

        return itemName;
    }

    public static String getItemSize(){
        String itemSize = "";

        return itemSize;
    }

    public static Integer getItemPrice(Long itemNo){
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
