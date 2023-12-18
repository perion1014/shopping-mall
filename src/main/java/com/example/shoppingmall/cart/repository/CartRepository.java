package com.example.shoppingmall.cart.repository;

import com.example.shoppingmall.cart.domain.Cart;

import java.util.ArrayList;

public interface CartRepository {

    ArrayList<Cart> getCartList(Long memberNo);
    ArrayList<String> getItemThumbnails();
    String getItemInfo();
    String getItemSize();

}
