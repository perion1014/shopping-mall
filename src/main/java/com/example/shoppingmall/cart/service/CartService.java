package com.example.shoppingmall.cart.service;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.dto.nonMemberCartAddDTO;
import com.example.shoppingmall.cart.repository.CartRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<CartReadDTO> getCartList(Long memberNo){
        List<Cart> dbCartList = cartRepository.getCartList(memberNo);
        List<CartReadDTO> cartDTOList = new ArrayList<CartReadDTO>();

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

    public void addCartItem(Long memberNo, Long itemNo, String cartAddItemSize, Integer addCartItemQuantiy){
    Cart cart = new Cart();
    cart.setMemberNo(memberNo);
    cart.setItemNo(itemNo);
    cart.setItemSize(cartAddItemSize);
    cart.setCartItemQuantity(addCartItemQuantiy);
    cartRepository.addCartItem(cart);
    }

    public List<nonMemberCartAddDTO> nonMemberAddCartItem(String thumbnail, String name, String size, Integer price, Integer Quantity, HttpServletRequest req){
        HttpSession session = req.getSession();
        List<nonMemberCartAddDTO> nonmemberCartList = new ArrayList<>();

        if(session.getAttribute("nonmemberCartList") == null){
            System.out.println("세션에 객체가 없을 경우로 진입");
            nonMemberCartAddDTO tempDTO = new nonMemberCartAddDTO();
            tempDTO.setCartNo(0L);
            tempDTO.setItemThumbnail(thumbnail);
            tempDTO.setItemName(name);
            tempDTO.setItemSize(size);
            tempDTO.setItemPrice(price);
            tempDTO.setItemQuantity(Quantity);
            nonmemberCartList.add(tempDTO);
            System.out.println("객체 담김 테스트 : " + nonmemberCartList.get(0).getCartNo());
            System.out.println("객체 담김 테스트 : " + nonmemberCartList.get(0).getItemThumbnail());
            System.out.println("객체 담김 테스트 : " + nonmemberCartList.get(0).getItemName());
            System.out.println("객체 담김 테스트 : " + nonmemberCartList.get(0).getItemSize());
            System.out.println("객체 담김 테스트 : " + nonmemberCartList.get(0).getItemPrice());
            System.out.println("객체 담김 테스트 : " + nonmemberCartList.get(0).getItemQuantity());

            return nonmemberCartList;
        } else{
            nonmemberCartList = (List<nonMemberCartAddDTO>)session.getAttribute("nonmemberCartList");
            nonMemberCartAddDTO tempDTO = new nonMemberCartAddDTO();
            tempDTO.setCartNo(Long.valueOf(nonmemberCartList.size()));
            tempDTO.setItemThumbnail(thumbnail);
            tempDTO.setItemName(name);
            tempDTO.setItemSize(size);
            tempDTO.setItemPrice(price);
            tempDTO.setItemQuantity(Quantity);
            nonmemberCartList.add(tempDTO);
            return nonmemberCartList;
        }

    }

}
