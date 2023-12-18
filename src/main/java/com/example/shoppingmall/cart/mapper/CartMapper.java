package com.example.shoppingmall.cart.mapper;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.service.CartService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface CartMapper {
    ArrayList<Cart> getCartList(@Param("memberNo") Long memberNo);
    ArrayList<String> getItemThumbnails();
    String getItemInfo();
    String getItemSize();
}
