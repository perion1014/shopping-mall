package com.example.shoppingmall.cart.service;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.dto.nonMemberCartAddDTO;
import com.example.shoppingmall.cart.repository.CartRepository;
import com.example.shoppingmall.item.dto.ItemPhotosDTO;
import com.example.shoppingmall.item.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    public List<CartReadDTO> getCartList(Long memberNo){
        List<Cart> dbCartList = cartRepository.getCartList(memberNo);
        List<CartReadDTO> cartDTOList = new ArrayList<CartReadDTO>();

        for(Cart cart: dbCartList){
            CartReadDTO cartReadDTO = new CartReadDTO();
            cartReadDTO.setMemberNo(cart.getMemberNo());
            cartReadDTO.setCartNo(cart.getCartNo());
            cartReadDTO.setItemNo(cart.getItemNo());
            cartReadDTO.setItemThumbnail(cartRepository.getItemThumbnails(cart.getItemNo()));
            cartReadDTO.setItemName(cartRepository.getItemName(cart.getItemNo()));
            cartReadDTO.setItemSize(cart.getItemSize());
            cartReadDTO.setItemPrice(cartRepository.getItemPrice(cart.getItemNo()));
            cartReadDTO.setItemQuantity(cart.getCartItemQuantity());
            //cartReadDTO.setItemPriceSum(cartReadDTO.getItemPrice() * cartReadDTO.getItemQuantity());
            //cartReadDTO.setOrderPriceSum((cartReadDTO.getItemPrice() * cartReadDTO.getItemQuantity()) - 1000);
            cartReadDTO.setItemPhotosDTO(ItemPhotosDTO.toItemPhotosDTO(itemRepository.findItemPhotosByItemNo(cartReadDTO.getItemNo())));
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
            nonMemberCartAddDTO tempDTO = new nonMemberCartAddDTO();
            tempDTO.setCartNo(0L);
            tempDTO.setItemThumbnail(thumbnail);
            tempDTO.setItemName(name);
            tempDTO.setItemSize(size);
            tempDTO.setItemPrice(price);
            tempDTO.setItemQuantity(Quantity);
            nonmemberCartList.add(tempDTO);

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

    public List<nonMemberCartAddDTO> nonMemberUpdateCartItem(Integer cartIndex, Integer changeQuantity, HttpSession session){
        List<nonMemberCartAddDTO> tempDTOList = (List<nonMemberCartAddDTO>)session.getAttribute("nonmemberCartList");
        nonMemberCartAddDTO tempDTO = tempDTOList.get(cartIndex);
        if(changeQuantity == 1){
            if(tempDTO.getItemQuantity() != 99){
                tempDTO.setItemQuantity(tempDTO.getItemQuantity() + changeQuantity);
            }
        } else if(changeQuantity == -1){
               if(tempDTO.getItemQuantity() != 1){
                tempDTO.setItemQuantity(tempDTO.getItemQuantity() + changeQuantity);
            }
        }
        tempDTOList.set(cartIndex, tempDTO);
        return tempDTOList;
    }

    public List<nonMemberCartAddDTO> nonMemberDeleteCartItem(Integer cartIndex, HttpSession session){

        List<nonMemberCartAddDTO> tempDTOList = (List<nonMemberCartAddDTO>)session.getAttribute("nonmemberCartList");
        System.out.println("메소드에서 전달받은 카트 인덱스 : " + cartIndex);
        tempDTOList.remove(cartIndex.intValue());

        for(int i = 0; i < tempDTOList.size(); i++){
            System.out.println("삭제 후 요소 순환 확인 : " + tempDTOList.get(i).getCartNo());
        }

        return tempDTOList;
    }

}
