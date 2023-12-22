package com.example.shoppingmall.cart.controller;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.member.domain.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private ArrayList<CartReadDTO> cartList;

    @GetMapping("/{memberNo}")
    public String showMemberCartList(@PathVariable(name = "memberNo") Long memberNo, Model model) {
        List<CartReadDTO> cartDTOList = cartService.getCartList(memberNo);
        model.addAttribute("cartDTOList", cartDTOList);
        return "carts/member-cart-list";
    }

    @PostMapping("/{memberNo}")
    public String addItemsToMemberCart(@PathVariable(name = "memberNo") Long memberNo,
                                       @RequestParam(name = "itemNo") Long itemNo,
                                       @RequestParam(name = "cartAddItemSize") String cartAddItemSize,
                                       @RequestParam(name = "addCartItemQuantiy") Integer addCartItemQuantiy){

        System.out.println("페이지에서 받아온 회원 번호 : " + memberNo);
        System.out.println("페이지에서 받아온 상품 번호 : " + itemNo);
        System.out.println("페이지에서 받아온 상품 사이즈 : " + cartAddItemSize);
        System.out.println("페이지에서 받아온 상품 수량 : " + addCartItemQuantiy);

        cartService.addCartItem(memberNo, itemNo, cartAddItemSize, addCartItemQuantiy);

        return "carts/itemInfo_Temp_CMS";
    }

    @PostMapping("/{memberNo}/{cartNo}/update")
    public String updateItemsInMemberCart(@RequestBody Map<String, String> jsonData, Model model, RedirectAttributes rttr){
        Integer currentQuantityNum = Integer.parseInt(jsonData.get("currentQuantityNum"));
        Long cartNo = Long.parseLong(jsonData.get("cartNo"));
        //Long memberNo = Long.parseLong(jsonData.get("memberNo"));
        cartService.updateCartItem(cartNo, currentQuantityNum);
        //List<CartReadDTO> cartDTOList = cartService.getCartList(memberNo);
        //model.addAttribute("cartDTOList", cartDTOList);
        //rttr.addFlashAttribute("cartDTOList", cartDTOList);
        //return "redirect:/carts/" + memberNo;
        return "carts/member-cart-list";
    }

    @PostMapping("/{memberNo}/{cartNo}/delete")
    public String deleteItemsInMemberCart(@RequestBody Map<String, String> jsonData, Model model){
        //System.out.println("deleteItemsInMemberCart 메소드 진입");
        Long cartNo = Long.parseLong(jsonData.get("cartNo"));
        //Long memberNo = Long.parseLong(jsonData.get("memberNo"));
        //System.out.println("fetch로 전달받은 장바구니 번호 : " + cartNo);
        //System.out.println("fetch로 전달받은 회원 번호 : " + memberNo);
        cartService.deleteCartItem(cartNo);
        return "carts/member-cart-list";
    }

    @GetMapping("/carts/item_info_temp")
    public String gotoItemInfo(Model model, HttpSession session){
        Long loginMemberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
        model.addAttribute("memberNo", loginMemberNo);
        return "carts/itemInfo_Temp_CMS";
    }

}
