package com.example.shoppingmall.cart.repository;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class MyBatisCartRepository implements CartRepository{

    private final CartMapper cartMapper;

    @Override
    public ArrayList<Cart> getCartList(Long memberNo) {
        ArrayList<Cart> cartList = cartMapper.getCartList(memberNo);
        return cartList;
    }

    @Override
    public ArrayList<String> getItemThumbnails() {
        return null;
    }

    @Override
    public String getItemInfo() {
        return null;
    }

    @Override
    public String getItemSize() {
        return null;
    }
}
