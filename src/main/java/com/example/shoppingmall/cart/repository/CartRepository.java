package com.example.shoppingmall.cart.repository;

import com.example.shoppingmall.cart.domain.Cart;

import java.util.ArrayList;

public interface CartRepository {

    ArrayList<Cart> getCartList(Long memberNo);
    String getItemThumbnails(Long itemNo);
    String getItemName(Long itemNo);
    Integer getItemPrice(Long itemNo);

    void updateCartItem(Long cartNo, Integer updateQuantity);

    void deleteCartItem(Long cartNo);

}
