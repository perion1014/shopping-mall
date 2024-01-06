package com.example.shoppingmall.order.controller;

import com.example.shoppingmall.cart.dto.CartDeleteDTO;
import com.example.shoppingmall.cart.dto.CartReadDTO;
import com.example.shoppingmall.cart.service.CartService;
import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.dto.ItemStockDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberDTO;
import com.example.shoppingmall.member.service.MemberService;
import com.example.shoppingmall.order.domain.MemberOrderDetail;
import com.example.shoppingmall.order.dto.*;
import com.example.shoppingmall.order.form.MemberOrderAdminViewForm;
import com.example.shoppingmall.order.form.MemberOrderPageForm;
import com.example.shoppingmall.order.form.MemberOrderViewForm;
import com.example.shoppingmall.order.service.MemberOrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

        System.out.println("컨트롤러에 도착 확인");

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

        System.out.println("재고 여부 : " + isStockEnough);

        Map<String, Object> responseData = new HashMap<>();

        if (isStockEnough == false) {   // 재고가 더 적은 cart 객체가 하나라도 있을 경우
            responseData.put("response", "선택하신 상품의 재고가 없습니다.");
            System.out.println(responseData.get("response"));
        } else {                // 모든 cart 장바구니 객체에 대하여 재고가 충분할 경우
            responseData.put("response", jsonData);
            System.out.println(responseData.get("response"));
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
            memberOrderDetailAddDTO.setItemName(jsonData.get(i).getItemName());
            memberOrderDetailAddDTO.setItemSize(jsonData.get(i).getItemSize());
            memberOrderDetailAddDTO.setItemQuantity(jsonData.get(i).getItemQuantity());
            memberOrderDetailAddDTO.setItemPrice(jsonData.get(i).getItemPrice());
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

    @GetMapping("/members/orders/create")
    public String tempMethod() {
        return "orders/member-order";
    }

    /* 3 */
    /* user */
    @PostMapping("/members/{memberNo}/orders/create-success")
    public String makeMemberOrderSuccess(@PathVariable(name="memberNo") Long memberNo,
                                         @ModelAttribute MemberOrderAddDTO memberOrderAddDTO,
                                         HttpServletRequest request,
                                         Model model) {

        HttpSession session = request.getSession();

        MemberOrderAddDTO memberOrderDTO = (MemberOrderAddDTO) session.getAttribute("memberOrderDTO");
        memberOrderDTO.setOrderAddressBasic(memberOrderAddDTO.getOrderAddressBasic());
        memberOrderDTO.setOrderAddressDetail(memberOrderAddDTO.getOrderAddressDetail());
        List<MemberOrderDetailAddDTO> memberOrderDetailAddDTOList = memberOrderDTO.getMemberOrderDetailAddDTOList();
        memberOrderService.saveMemberOrder(memberNo, memberOrderDTO);
        Long maxMemberOrderNo = memberOrderService.getMaxMemberOrderNo();
        memberOrderService.saveMemberOrderDetail(maxMemberOrderNo, memberOrderDetailAddDTOList);
        model.addAttribute("memberOrderNo", maxMemberOrderNo);

        for (MemberOrderDetailAddDTO memberOrderDetailAddDTO: memberOrderDetailAddDTOList) {
            CartDeleteDTO cartDeleteDTO = new CartDeleteDTO(memberNo, memberOrderDetailAddDTO.getItemNo(), memberOrderDetailAddDTO.getItemSize());
            cartService.deleteCartItemByItemNoAndItemSize(cartDeleteDTO);
        }
        session.setAttribute("memberOrderDTO", null);

        return "orders/member-order-success-test";
    }


    /* user */
    @GetMapping("/members/{memberNo}/orders")
    public String showMemberOrderList(@PathVariable(name="memberNo") Long memberNo,
                                      @RequestParam(value="page", required=false, defaultValue="1") int page,
                                      @ModelAttribute MemberOrderViewForm memberOrderViewForm,
                                      HttpServletRequest request,
                                      Model model) {

        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null ) {
            return "redirect:/members/login";
        }
        memberOrderViewForm.setMemberNo(memberNo);
        model.addAttribute("pageSettings", memberOrderService.setMemberOrderListPage(page, memberOrderViewForm));
        List<MemberOrderDTO> memberOrderDTOList = memberOrderService.getMemberOrderListPage(page, memberOrderViewForm);
        List<Integer> priceSumList = new ArrayList<>();
        for (MemberOrderDTO memberOrderDTO: memberOrderDTOList) {
            Integer priceSum = 0;
            for (int i = 0; i < memberOrderDTO.getMemberOrderDetailDTOList().size(); i++) {
                priceSum += memberOrderDTO.getMemberOrderDetailDTOList().get(i).getItemPrice() * memberOrderDTO.getMemberOrderDetailDTOList().get(i).getItemQuantity();
            }
            priceSumList.add(priceSum);
        }
        for (int i = 0; i < priceSumList.size(); i++) {
            memberOrderDTOList.get(i).setPriceSum(priceSumList.get(i));
        }
        model.addAttribute("memberOrderDTOList", memberOrderDTOList);
        return "orders/member-order-list-test";
    }

    /* user */
    @PostMapping("/members/{memberNo}/orders")
    public String cancelMemberOrder(@PathVariable(name = "memberNo") Long memberNo,
                                    @RequestParam(name = "orderNo") Long orderNo) {
        memberOrderService.cancelMemberOrder(orderNo);
        return "redirect:/members/" + memberNo + "/orders";
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
        MemberOrderDTO memberOrderDTO = memberOrderService.findMemberOrderByNo(orderNo);
        for (MemberOrderDetailDTO memberOrderDetailDTO: memberOrderDTO.getMemberOrderDetailDTOList()) {
            String itemThumb = itemService.getItemThumbByNo(memberOrderDetailDTO.getItemNo());
            memberOrderDetailDTO.setItemThumb(itemThumb);
        }
        model.addAttribute("memberOrderDTO", memberOrderDTO);
        return "orders/member-order-detail-test";
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

    @GetMapping("/orders/admin/members")
    public String showMemberOrderList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                      @RequestParam(value = "searchCategory", defaultValue = "member_no") String searchCategory,
                                      @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
                                      HttpServletRequest request,
                                      Model model) {

        HttpSession session = request.getSession();

        System.out.println("searchCategory: " + searchCategory);
        System.out.println("searchKeyword: " + searchKeyword);

        MemberOrderAdminViewForm memberOrderAdminViewForm = new MemberOrderAdminViewForm();

        memberOrderAdminViewForm.setSearchCategory(searchCategory);

        if ( searchKeyword == null || searchKeyword.replace("+", "").replace(" ", "").equals("")) {
            model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
            List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
            model.addAttribute("memberOrderDTOList", memberOrderDTOList);
        } else {
            if (searchCategory.equals("member_order_no") || searchCategory.equals("member_no")) {                           // Long 타입
                try {
                    memberOrderAdminViewForm.setSearchKeywordLong(Long.parseLong(searchKeyword));
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    return "admins/admins-order-pym";
                }
                ////////////////////////////////////////////////////////////////////////////// 예외 처리 끝
                if (searchCategory.equals("member_order_no")) {
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearchLong(page, memberOrderAdminViewForm));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearchLong(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                } else {
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearchLong2(page, memberOrderAdminViewForm));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearchLong2(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                }
                ////////////////////////////////////////////////////////////////////////////// DTO 담기
            } else if (searchCategory.equals("order_postal_code")) {                                                        // Integer 타입
                try {
                    memberOrderAdminViewForm.setSearchKeywordInteger(Integer.parseInt(searchKeyword));
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    return "admins/admins-order-pym";
                }
                ////////////////////////////////////////////////////////////////////////////// 예외 처리 끝
                model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearchInteger(page, memberOrderAdminViewForm));
                List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearchInteger(page, memberOrderAdminViewForm);
                model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                ////////////////////////////////////////////////////////////////////////////// DTO 담기
            } else {                                                                                                        // String 타입
                try {
                    memberOrderAdminViewForm.setSearchKeywordString(searchKeyword);
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPage(page));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPage(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                    return "admins/admins-order-pym";
                }
                ////////////////////////////////////////////////////////////////////////////// 예외 처리 끝
                if (searchCategory.equals("receiver_name")) {
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearch(page, memberOrderAdminViewForm));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearch(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                } else {
                    model.addAttribute("pageSettings", memberOrderService.setMemberOrderAdminListPageBySearch2(page, memberOrderAdminViewForm));
                    List<MemberOrderAdminViewDTO> memberOrderDTOList = memberOrderService.getMemberOrderAdminListPageBySearch2(page, memberOrderAdminViewForm);
                    model.addAttribute("memberOrderDTOList", memberOrderDTOList);
                }
                ////////////////////////////////////////////////////////////////////////////// DTO 담기
            }
        }
        return "admins/admins-order-pym";
    }

    /* admin */
    @GetMapping("/orders/admin/members/{orderNo}")
    public String showMemberOrderDetail(@PathVariable(name="orderNo") Long orderNo,
                                        Model model) {
        List<MemberOrderDetailDTO> memberOrderDetailDTOList = memberOrderService.getMemberOrderDetailList(orderNo);
        model.addAttribute("memberOrderDetailDTOList", memberOrderDetailDTOList);

        Integer priceSum = 0;
        for (MemberOrderDetailDTO memberOrderDetailDTO: memberOrderDetailDTOList) {
            priceSum += memberOrderDetailDTO.getItemPrice() * memberOrderDetailDTO.getItemQuantity();
        }
        model.addAttribute("priceSum", priceSum);

        return "admins/admins-order-detail-pym";
    }

    /* admin */
    @GetMapping("/orders/admin/members/{orderNo}/cancel")
    public String cancelMemberOrderAdmin(@PathVariable(name="orderNo") Long orderNo) {
        memberOrderService.cancelMemberOrder(orderNo);
        return "redirect:/orders/admin/members";
    }


}
