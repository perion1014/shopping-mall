package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.cart.dto.CartDeleteDTO;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberDTO;
import com.example.shoppingmall.member.service.MemberService;
import com.example.shoppingmall.order.dto.*;
import com.example.shoppingmall.order.service.MemberOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderControllerPYM {

    private final ItemService itemService;
    private final MemberOrderService memberOrderService;
    private final CartService cartService;


    /* 1 */
    /* user */
    @PostMapping("/members/{memberNo}/orders/check-itemstock")
    @ResponseBody
    public Map<String, Object> checkMemberOrderItemStock(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                                         @PathVariable(name="memberNo") Integer memberNo){

//        System.out.println(jsonData);
//        System.out.println(memberNo);
        System.out.println("컨트롤러에 도착 확인");

//        for(int i =0; i <jsonData.size(); i++){
//            System.out.println(jsonData.get(i).getItemNo());
//            System.out.println(jsonData.get(i).getItemSize());
//            System.out.println(jsonData.get(i).getItemQuantity());
//        }

        boolean isStockEnough = true;   // 재고가 충분한지? -> default value: true

        for (int i = 0; i < jsonData.size(); i++) {
            ItemStockDTO itemStockDTO = new ItemStockDTO();
            itemStockDTO.setItemNo(jsonData.get(i).getItemNo());
            itemStockDTO.setItemSize(jsonData.get(i).getItemSize());
            Integer itemStockValue = itemService.getItemStockValueByItemNoAndItemSize(itemStockDTO);    // 해당 상품의 재고
            if ( jsonData.get(i).getItemQuantity() > itemStockValue ) { // 선택한 수량이 재고보다 더 많으면
                isStockEnough = false;                                  // 충분하지 않음!
            }
        }

        Map<String, Object> responseData = new HashMap<>();

        if (!isStockEnough) {   // 재고가 더 적은 cart 객체가 하나라도 있을 경우
            responseData.put("response", "선택하신 상품의 재고가 없습니다.");
        } else {                // 모든 cart 장바구니 객체에 대하여 재고가 충분할 경우
            responseData.put("response", jsonData);
        }
        return responseData;
    }



    /* 2 */
    /* user */
    @PostMapping("/members/{memberNo}/orders/create")
    public String goToMemberOrderPage(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                      @PathVariable(name="memberNo") Long memberNo,
                                      HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }

        List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList = new ArrayList<>();
        for (int i = 0; i < jsonData.size(); i++) {
            MemberOrderDetailAddDTO memberOrderDetailAddDTO = new MemberOrderDetailAddDTO();
            memberOrderDetailAddDTO.setItemNo(jsonData.get(i).getItemNo());
            memberOrderDetailAddDTO.setItemSize(jsonData.get(i).getItemSize());
            memberOrderDetailAddDTO.setItemQuantity(jsonData.get(i).getItemQuantity());
            memberOrderDetailAddDTOList.add(memberOrderDetailAddDTO);
        }

        Member member = (Member) session.getAttribute("loginMember");
        MemberOrderAddDTO memberOrderAddDTO = new MemberOrderAddDTO();
        memberOrderAddDTO.setMemberNo(memberNo);
        memberOrderAddDTO.setOrderHp(member.getMemberHp());
        memberOrderAddDTO.setOrderPostalCode(member.getMemberPostalCode());
        memberOrderAddDTO.setOrderAddressBasic(member.getMemberAddressBasic());
        memberOrderAddDTO.setOrderAddressDetail(member.getMemberAddressDetail());
        memberOrderAddDTO.setReceiverName(member.getMemberName());
        memberOrderAddDTO.setMemberOrderDetailAddDTOList(memberOrderDetailAddDTOList);

        session.setAttribute("memberOrderDTO", memberOrderAddDTO);

        return "orders/member-order";
    }

    @GetMapping("/temp")
    public String tempMethod() {
        return "orders/member-order";
    }

    /* 3 */
    /* user */
    @PostMapping("/members/{memberNo}/orders/create-success")
    public String makeMemberOrderSuccess(@PathVariable(name="memberNo") Long memberNo,
                                         HttpServletRequest request,
                                         Model model) {

        HttpSession session = request.getSession();

        MemberOrderAddDTO memberOrderAddDTO = (MemberOrderAddDTO) session.getAttribute("memberOrderDTO");
        List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList = memberOrderAddDTO.getMemberOrderDetailAddDTOList();
        memberOrderService.saveMemberOrder(memberNo, memberOrderAddDTO);
        Long maxMemberOrderNo = memberOrderService.getMaxMemberOrderNo();
        memberOrderService.saveMemberOrderDetail(maxMemberOrderNo, memberOrderDetailAddDTOList);
        model.addAttribute("memberOrderNo", maxMemberOrderNo);

        for (MemberOrderDetailAddDTO memberOrderDetailAddDTO: memberOrderDetailAddDTOList) {
            CartDeleteDTO cartDeleteDTO = new CartDeleteDTO(memberNo, memberOrderDetailAddDTO.getItemNo(), memberOrderDetailAddDTO.getItemSize());
            cartService.deleteCartItemByItemNoAndItemSize(cartDeleteDTO);
        }


        return "orders/member-order-success-test";
    }


    /* user */
    @GetMapping("/members/{memberNo}/orders")
    public String showMemberOrderList(@PathVariable(name="memberNo") Long memberNo,
                                      HttpServletRequest request,
                                      Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null ) {
            return "redirect:/members/login";
        }

        List<MemberOrderDTO> memberOrderDTOList = memberOrderService.findMemberOrderList(memberNo);
        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
        return null;
    }

    /* user */
    @GetMapping("/members/{memberNo}/orders/{orderNo}")
    public String showMemberOrderDetail(@PathVariable(name="memberNo") Long memberNo,
                                        @PathVariable(name="orderNo") Long orderNo,
                                        HttpServletRequest request,
                                        Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null ) {
            return "redirect:/members/login";
        }
        MemberOrderDetailDTO memberOrderDetailDTO = memberOrderService.findMemberOrderDetail(orderNo);
        model.addAttribute("memberOrderDetailDTO", memberOrderDetailDTO);
        return null;
    }

    //..
    @GetMapping("/order/member/test")
    public String goToMemberOrderTest() {
        return "/orders/member-order";
    }

    //..
    @GetMapping("/order/nonmember/test")
    public String goToNonmemberOrderTest() {
        return "/orders/nonmember-order";
    }

    @GetMapping("/members/{memberNo}/orders/create")
    public String goToInputMemberOrder(@PathVariable(name="memberNo") Long memberNo) {

        System.out.println("goToInputMemberOrder 진입");

        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }


    @GetMapping("/admin/members")
    public String showMemberOrderList(Model model) {
        List<MemberOrderDTO> memberOrderDTOList = memberOrderService.getMemberOrderList();
        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
        return "admins/admins-order";    // html 파일이 생성되면 그때 수정할 예정.
    }

    @PostMapping("/admin/members/{orderNo}")
    public String showMemberOrderDetail(@PathVariable(name="orderNo") Long orderNo, Model model) {
        MemberOrderDetailDTO memberOrderDetailDTO = memberOrderService.getMemberOrderDetail();
        model.addAttribute("memberOrderDetailDTO", memberOrderDetailDTO);
        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }


}
