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
            cartReadDTO.setMemberNo(cart.getMemberNo());
            cartReadDTO.setCartNo(cart.getCartNo());
            cartReadDTO.setItemThumbnail(cartRepository.getItemThumbnails(cart.getItemNo()));
            cartReadDTO.setItemName(cartRepository.getItemName(cart.getItemNo()));
            cartReadDTO.setItemSize(cart.getItemSize());
            cartReadDTO.setItemPrice(cartRepository.getItemPrice(cart.getItemNo()));
            cartReadDTO.setItemQuantity(cart.getCartItemQuantity());
            cartReadDTO.setItemPriceSum(cartReadDTO.getItemPrice() * cartReadDTO.getItemQuantity());
            cartReadDTO.setOrderPriceSum((cartReadDTO.getItemPrice() * cartReadDTO.getItemQuantity()) - 1000);
            cartDTOList.add(cartReadDTO);
        }

        return cartDTOList;
    }

    public void updateCartItem(Long cartNo, Integer updateQuantity){
    cartRepository.updateCartItem(cartNo, updateQuantity);
    }

    public void deleteCartItem(Long cartNo){
        cartRepository.deleteCartItem(cartNo);
    }

}
