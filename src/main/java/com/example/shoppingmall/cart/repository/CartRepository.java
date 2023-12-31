package com.example.shoppingmall.cart.repository;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartDeleteDTO;

import java.util.ArrayList;
import java.util.List;

public interface CartRepository {

    ArrayList<Cart> getCartList(Long memberNo);
    String getItemThumbnails(Long itemNo);
    String getItemName(Long itemNo);
    Integer getItemPrice(Long itemNo);

    void updateCartItem(Long cartNo, Integer updateQuantity);

    void deleteCartItem(Long cartNo);

    void addCartItem(Cart cart);

    void deleteCartItemByItemNoAndItemSize(CartDeleteDTO cartDeleteDTO);

    List<Cart> getAllCarts(Long memberNo);
}
