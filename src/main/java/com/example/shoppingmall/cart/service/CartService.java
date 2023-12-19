package com.example.shoppingmall.cart.service;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    public ArrayList<CartReadDTO> getCartList(Long memberNo){
        ArrayList<Cart> dbCartList = cartRepository.getCartList(memberNo);
        //System.out.println("장바구니 조회 쿼리로 값을 받아왔나요? : " + !dbCartList.isEmpty());
        ArrayList<CartReadDTO> cartDTOList = new ArrayList<CartReadDTO>();

        for(Cart cart: dbCartList){
            CartReadDTO cartReadDTO = new CartReadDTO();
            cartReadDTO.setItemThumbnail(cartRepository.getItemThumbnails(cart.getItemNo()));
            System.out.println("썸네일 : " + cartReadDTO.getItemThumbnail());
            cartReadDTO.setItemName(cartRepository.getItemName(cart.getItemNo()));
            System.out.println("상품 이름 : " + cartReadDTO.getItemName());
            cartReadDTO.setItemSize(cart.getItemSize());
            System.out.println("상품 사이즈 : " + cartReadDTO.getItemSize());
            cartReadDTO.setItemPrice(cartRepository.getItemPrice(cart.getItemNo()));
            System.out.println("상품 가격 : " + cartReadDTO.getItemPrice());
            cartReadDTO.setItemQuantity(cart.getCartItemQuantity());
            System.out.println("상품 수량 : " + cartReadDTO.getItemQuantity());
            cartReadDTO.setItemPriceSum(cartReadDTO.getItemPrice() * cartReadDTO.getItemQuantity());
            System.out.println("상품 총액 : " + cartReadDTO.getItemPriceSum());
            cartReadDTO.setOrderPriceSum((cartReadDTO.getItemPrice() * cartReadDTO.getItemQuantity()) - 1000);
            System.out.println("주문 총액 : " + cartReadDTO.getOrderPriceSum());
            cartDTOList.add(cartReadDTO);
        }

        return cartDTOList;
    }

    public String getItemThumbnail(Long itemNo){
         return cartRepository.getItemThumbnails(itemNo);
    }

    public String getItemName(Long itemNo){
        String itemName = "";

        return itemName;
    }

    public String getItemSize(){
        String itemSize = "";

        return itemSize;
    }

    public Integer getItemPrice(Long itemNo){
        Integer itemPrice = 0;

        return itemPrice;
    }

    public Integer getItemQuantity(){
        Integer itemQuantity = 0;

        return itemQuantity;
    }

    public Integer getItemPriceSum(){
        Integer itemPriceSum = 0;

        return itemPriceSum;
    }

    public Integer getOrderPriceSum(){
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
