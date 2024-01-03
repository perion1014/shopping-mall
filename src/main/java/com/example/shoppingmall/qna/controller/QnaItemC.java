package com.example.shoppingmall.qna.controller;

import com.example.shoppingmall.item.service.ItemService;
import com.example.shoppingmall.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class QnaItemC {


    private final ItemService itemService;
    private final QnaService qnaService;


    @GetMapping("/{itemNo}/qna")
    public  String showItemQnaList(@RequestParam(value="page", required=false, defaultValue="1") int page,
                                  @PathVariable(name="itemNo") Long itemNo, Model model) {

        model.addAttribute("itemNo", itemNo);

        model.addAttribute("pageSettings", qnaService.setQnaListPageByItemNo(page,itemNo));
        model.addAttribute("qnaListByPaging",qnaService.getQnaListByItemNo(page, itemNo));

//        List<QnaDTO> qnaByItemNo = qnaService.getQnaByItemNo(itemNo);
//        model.addAttribute("qnaByItemNo",qnaByItemNo);

        return "qna/item-qna";
    }


    @GetMapping("/{itemNo}/qna/{qnaNo}")
    public  String showItemQnaDetail(@PathVariable(name="itemNo") Long itemNo,
                                  @PathVariable(name="qnaNo") Long qnaNo, Model model) {
        model.addAttribute("qnaDetail", qnaService.getQnaInfo(qnaNo));

        return "qna/test";
    }



}
