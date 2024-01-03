package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderControllerPYM {

    private final MemberService memberService;
    private final MemberOrderService memberOrderService;
    private final CartService cartService;


    @PostMapping("/members/{memberNo}/orders/check-itemstock")
    @ResponseBody
    public Map<String, Object> checkMemberOrderItemStock(@RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                                         @PathVariable Integer memberNo){

//        System.out.println(jsonData);
//        System.out.println(memberNo);
        System.out.println("컨트롤러에 도착 확인");

        for(int i =0; i <jsonData.size(); i++){
            System.out.println(jsonData.get(i).getItemName());
            System.out.println(jsonData.get(i).getItemSize());
            System.out.println(jsonData.get(i).getItemQuantity());
        }

        for (int i = 0; i < jsonData.size(); i++) {

        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("response", "선택하신 상품의 재고가 없습니다.");

        return responseData;
    }




    /* user */
    @PostMapping("/members/{memberNo}/orders/create")
    public String goToMemberOrderPage(@PathVariable(name="memberNo") Long memberNo,
                                      @ModelAttribute CartReadDTO cartReadDTO,
                                      Model model,
                                      HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }

        List<CartReadDTO> cartDTOList = cartService.getCartList(memberNo);

        model.addAttribute("cartDTOList", cartDTOList);
        return "orders/member-order-pym";
    }

    /* user */
    @PostMapping("/members/{memberNo}/orders/create-order")
    public String makeMemberOrder(@PathVariable(name="memberNo") Long memberNo,
                                  @RequestBody List<MemberOrderItemStockCheckDTO> jsonData,
                                  Model model) {
        model.addAttribute("cartDTOList", jsonData);

        return null;
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

    @PostMapping("/{memberNo}/create")
    public String makeMemberOrder(@PathVariable(name="memberNo") Long memberNo, @ModelAttribute MemberOrderAddDTO memberOrderAddDTO, @ModelAttribute MemberOrderDetailAddDTO memberOrderDetailAddDTO) {
        memberOrderService.saveMemberOrder(memberOrderAddDTO);
        return null;    // html 파일이 생성되면 그때 수정할 예정.
    }

    @GetMapping("/{memberNo}/create-success")
    public String makeMemberOrderSuccess(@PathVariable(name="memberNo") Long memberNo) {
        return null;
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
