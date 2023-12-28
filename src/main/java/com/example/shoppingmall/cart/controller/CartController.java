package com.example.shoppingmall.cart.controller;

import com.example.shoppingmall.cart.domain.Cart;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.dto.nonMemberCartAddDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

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

        cartService.addCartItem(memberNo, itemNo, cartAddItemSize, addCartItemQuantiy);
        //return "redirect:/carts/" + memberNo;
        return "redirect:/carts/carts/item_info_temp";
    }

    @PostMapping("/{memberNo}/{cartNo}/update")
    @ResponseBody
    public Map<String, Object> updateItemsInMemberCart(@RequestBody Map<String, String> jsonData, Model model, RedirectAttributes rttr){
        Integer currentQuantityNum = Integer.parseInt(jsonData.get("currentQuantityNum"));
        //버튼 누른 이후 값 전달받는 것 확인
        Long cartNo = Long.parseLong(jsonData.get("cartNo"));
        Long itemNo = Long.parseLong(jsonData.get("itemNo"));
        String itemSize = jsonData.get("itemSize");
//        System.out.println("JSON으로 전송받은 사이즈 : " + jsonData.get("itemSize"));
//        System.out.println("JSON으로 전송받은 아이템 번호 : " + jsonData.get("itemNo"));
        //Integer itemStockValue = cartService.getItemStockValue(itemNo, itemSize);
        //System.out.println(itemStockValue);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("response", "잘 받았습니다.");

        cartService.updateCartItem(cartNo, currentQuantityNum);
        return responseData;
    }

    @PostMapping("/{memberNo}/{cartNo}/delete")
    public String deleteItemsInMemberCart(@RequestBody Map<String, String> jsonData, Model model){
        Long cartNo = Long.parseLong(jsonData.get("cartNo"));
        cartService.deleteCartItem(cartNo);
        return "carts/member-cart-list";
    }

    @GetMapping("/carts/item_info_temp")
    public String gotoItemInfo(){
        return "carts/itemInfo_Temp_CMS";
    }

    @GetMapping("")
    public String showNonMemberCartList(){
        return "carts/nonmember-cart-list";
    }

    @PostMapping("")
    public String addItemsToNonMemberCart(@RequestParam(name = "nonMemberCartThumbnail") String itemThumbnail,
                                          @RequestParam(name = "nonMemberCartItemName") String itemName,
                                          @RequestParam(name = "nonMemberCartItemSize") String itemSize,
                                          @RequestParam(name = "nonMemberCartItemPrice") Integer itemPrice,
                                          @RequestParam(name = "nonMemberAddCartItemQuantiy") Integer itemQuantity,
                                          HttpServletRequest req){

        HttpSession session = req.getSession();
        session.setAttribute("nonmemberCartList", cartService.nonMemberAddCartItem(itemThumbnail, itemName, itemSize, itemPrice, itemQuantity, req));

        return "carts/itemInfo_Temp_CMS";
    }

    @PostMapping("/{cartNo}/update")
    public String updateItemsInNonMemberCart(@RequestBody Map<String, String> jsonData, HttpServletRequest req) {
        Integer changeQuantity = Integer.parseInt(jsonData.get("changeQuantity"));
        Integer cartIndex = Integer.parseInt(jsonData.get("cartListIndex"));

        HttpSession session = req.getSession();
        session.setAttribute("nonmemberCartList", cartService.nonMemberUpdateCartItem(cartIndex, changeQuantity, session));
        //System.out.println("Java로 전송받은 변경 수량 : " + changeQuantity);
        //System.out.println("Java로 전송받은 리스트 인덱스 : " + cartIndex);

        return "carts/nonmember-cart-list";
    }

    @PostMapping("/{cartNo}/delete")
    public String deleteItemsInNonMemberCart(@RequestBody Map<String, String> jsonData, HttpServletRequest req){
        Integer cartIndex = Integer.parseInt(jsonData.get("cartNo"));

        HttpSession session = req.getSession();
        session.setAttribute("nonmemberCartList", cartService.nonMemberDeleteCartItem(cartIndex, session));

        return "carts/nonmember-cart-list";
    }
}






//@Controller
//@RequiredArgsConstructor
//public class ItemControllerTemp{
//    @GetMapping("/items/1")
//    public String gotoItemInfoTemp(){
//    return "carts/itemInfo_Temp_CMS";
//    }
//}