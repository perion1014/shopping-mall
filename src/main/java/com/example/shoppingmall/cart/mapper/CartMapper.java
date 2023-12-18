package com.example.shoppingmall.cart.mapper;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.service.CartService;

import java.util.ArrayList;

public interface CartMapper {
    ArrayList<Cart> showCartList();
    ArrayList<String> getItemThumbnails();
    String getItemInfo();
    String getItemSize();
}
