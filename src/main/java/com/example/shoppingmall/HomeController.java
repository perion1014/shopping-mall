package com.example.shoppingmall;

import com.example.shoppingmall.item.dto.ItemDTO;
import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String goHome(@SessionAttribute(name="loginMember", required = false) Member loginMember, Model model, HttpServletRequest request){


        List<ItemDTO> itemDTOList = itemService.findAllItems();
        model.addAttribute("itemDTOList", itemDTOList);

        HttpSession session = request.getSession();
        session.setAttribute("ifSearched", false);
        session.setAttribute("ifCategorySelected", true);

        List<ItemDTO> itemDTOListByReview = itemService.findAllItemsByReview();
        if (itemDTOListByReview == null || itemDTOListByReview.isEmpty() || itemDTOListByReview.size() < 3) {
            itemDTOListByReview = itemDTOList;
        }
        model.addAttribute("itemDTOListByReview", itemDTOListByReview);


        //세션에 회원 데이터가 없으면 일반 홈으로 이동
        if(loginMember == null){
            return "home";
        }

        ///회원 세션이 회원 전용 홈으로 이동
        model.addAttribute("member",loginMember);
        return "login-home";
    }
}

