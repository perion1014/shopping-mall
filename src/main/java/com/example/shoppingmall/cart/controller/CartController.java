package com.example.shoppingmall.cart.controller;

import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.dto.ItemPhotosDTO;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.service.QnaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ItemService itemService;
    private final QnaService qnaService;

    //회원 - 장바구니 목록 조회
    @GetMapping("/{memberNo}")
    public String showMemberCartList(@PathVariable(name = "memberNo") Long memberNo, Model model) {
        List<CartReadDTO> cartDTOList = cartService.getCartList(memberNo);
        //cartDTOList[0].itemPhotosDTO.itemThumb
        model.addAttribute("cartDTOList", cartDTOList);
        return "carts/member-cart-list-test";
    }

    //회원 - 장바구니 추가
    @PostMapping("/{memberNo}")
    public String addItemsToMemberCart(@PathVariable(name = "memberNo", required = false) Long memberNo,
                                       @RequestParam(name = "itemNo", required = false) Long itemNo,
                                       @RequestParam(name = "itemSize", required = false) String itemSize,
                                       @RequestParam(name = "itemQuantity", required = false) Integer itemQuantity,
                                       Model model){

        boolean ifAdded = cartService.addCartItem(memberNo, itemNo, itemSize, itemQuantity);
        model.addAttribute("ifAdded", ifAdded);

        System.out.println("ifAdded: " + ifAdded);
        if (ifAdded == true) {
            return "redirect:/items/" + itemNo;
        } else {
            List<QnaDTO> qnaByItemNo = qnaService.getQnaByItemNo(itemNo);
            model.addAttribute("qnaByItemNo",qnaByItemNo);
            /////////////////////////////////////////////////////////////////////////
            ItemDTO itemDTO = itemService.findItemByNo2(itemNo);
            ItemPhotosDTO itemPhotosDTO = itemService.findItemPhotosByNo(itemNo);
            List<ItemStockDTO> itemStockDTOList = itemService.findItemStockListByNo(itemNo);
            model.addAttribute("itemDTO", itemDTO);
            model.addAttribute("itemPhotosDTO", itemPhotosDTO);
            model.addAttribute("itemStockDTOList", itemStockDTOList);
            return "items/item-detail";
        }
    }

    //회원 - 장바구니 항목 상품 수량 변경 및 쿼리에 적용
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

    //회원 - 장바구니 개별 항목 삭제
    @PostMapping("/{memberNo}/{cartNo}/delete")
    public String deleteItemsInMemberCart(@RequestBody Map<String, String> jsonData, Model model){
        Long cartNo = Long.parseLong(jsonData.get("cartNo"));
        cartService.deleteCartItem(cartNo);
        return "carts/member-cart-list";
    }

    //비회원 - 장바구니 목록 조회
    @GetMapping("")
    public String showNonMemberCartList(){
        return "carts/nonmember-cart-list-test";
    }

    //비회원 - 장바구니 추가
    @PostMapping("")
    public String addItemsToNonMemberCart(@RequestParam(name = "itemThumbnail", required = false) String itemThumbnail,
                                          @RequestParam(name = "itemName", required = false) String itemName,
                                          @RequestParam(name = "itemSize", required = false) String itemSize,
                                          @RequestParam(name = "itemPrice", required = false) Integer itemPrice,
                                          @RequestParam(name = "itemQuantity", required = false) Integer itemQuantity,
                                          @RequestParam(name = "itemNo", required = false) Long itemNo,
                                          HttpServletRequest req){

        System.out.println("비회원 - 받아온 썸네일 : " + itemThumbnail);
        System.out.println("비회원 - 받아온 상품이름 : " + itemName);
        System.out.println("비회원 - 받아온 상품사이즈 : " + itemSize);
        System.out.println("비회원 - 받아온 상품가격 : " + itemPrice);
        System.out.println("비회원 - 받아온 상품수량 : " + itemQuantity);
        System.out.println("비회원 - 받아온 상품번호 : " + itemNo);

        String itemThumb = itemService.getItemThumbByNo(itemNo);
        //System.out.println("db에서 받아온 썸넬 : " + itemThumb);

        HttpSession session = req.getSession();
        session.setAttribute("nonmemberCartList", cartService.nonMemberAddCartItem(itemThumb, itemName, itemSize, itemPrice, itemQuantity, itemNo, req));

        return "redirect:/items/" + itemNo;
    }

    //비회원 - 장바구니 수량 변경 및, 세션의 해당 장바구니 항목에 변경된 수량 적용
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

    // 비회원 - 장바구니 개별 항목 삭제
    @PostMapping("/{cartNo}/delete")
    public String deleteItemsInNonMemberCart(@RequestBody Map<String, String> jsonData, HttpServletRequest req){
        Integer cartIndex = Integer.parseInt(jsonData.get("cartNo"));

        HttpSession session = req.getSession();
        session.setAttribute("nonmemberCartList", cartService.nonMemberDeleteCartItem(cartIndex, session));

        return "carts/nonmember-cart-list";
    }


    //페이지 개발 완료 - 회원 장바구니 목록
    @GetMapping("/member/test")
    public String goToMemberTestPage(){
        return "carts/member-cart-list-test";
    }

    //페이지 개발 완료 - 비회원 장바구니 목록
    @GetMapping("/nonmember/test")
        public String goToNonMemberTestPage(){
        return "carts/nonmember-cart-list-test";
    }

    //페이지 개발 완료 - 회원 주문 성공 페이지
    @GetMapping("/member/order-success-test")
        public String goToMemberOrderSuccessTest(){
            return "member-order-success";
    }

    //페이지 개발 완료 - 비회원 주문 성공 페이지
    @GetMapping("/nonmember/order-success-test")
    public String goToNonmemberOrderSuccessTest(){
        return "orders/nonmember-order-success-test";
    }

    @GetMapping("/member/order-list-test")
    public String goToMemberOrderListTest(){
        return "member-order-list";
    }

    @GetMapping("/member/order-detail-test")
    public String goToMemberOrderDetailTest(){
        return "member-order-detail";
    }

    //페이지 개발 완료 - 비회원 주문 조회 입력 페이지
    @GetMapping("/orders/non-members")
    public String goToNonmemberOrderCheckTest(){
        return "nonmember-order-check";
    }

    @GetMapping("/nonmember/order-detail-check-test")
    public String goToNonmemberOrderDetailCheckTest(){
        return "orders/nonmember-order-detail-check-test";
    }

    //페이지 개발 완료 - 비회원 주문 삭제 성공 페이지
    @GetMapping("/orders/delete-sucess")
    public String goToNonmemberOrderDeleteSuccessTest(){
        return "orders/nonmember-order-delete-success-test";
    }


}

