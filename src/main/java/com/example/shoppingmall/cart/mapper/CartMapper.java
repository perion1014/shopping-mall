package com.example.shoppingmall.cart.mapper;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartDeleteDTO;
import com.example.shoppingmall.cart.service.CartService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface CartMapper {
    ArrayList<Cart> getCartList(@Param("memberNo") Long memberNo);
    String getItemThumbnails(@Param("itemNo") Long itemNo);
    String getItemName(@Param("itemNo") Long itemNo);
    Integer getItemPrice(@Param("itemNo") Long itemNo);

    void updateCartItem(@Param("cartNo") Long cartNo, @Param("updateQuantity") Integer updateQuantity);

    void deleteCartItem(@Param("cartNo") Long cartNo);

    void addCartItem(Cart cart);

    void deleteCartItemByItemNoAndItemSize(CartDeleteDTO cartDeleteDTO);
}
