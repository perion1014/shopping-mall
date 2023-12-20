package com.example.shoppingmall.notify.controller;

import com.example.shoppingmall.notify.dto.NoticeAddDTO;
import com.example.shoppingmall.notify.dto.NoticeListDTO;
import com.example.shoppingmall.notify.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice")//절대경로
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    @GetMapping("/admin")
    public String showNoticeList(Model model){
//        List<NoticeListDTO> noticeListDTO = noticeService.findAllNotice();
        model.addAttribute("noticeListDTOList", noticeService.findAllNotice());
        return "admins/notice/admins-notice";
    }
    @GetMapping("/admin/{noticeNo}")
    public String showNoticeDetail(@PathVariable(name="noticeNo")Integer noticeNo, Model model){
        return "admins/notice/admins-notice-detail";
    }
    @GetMapping("/admin/add")
    public String goToRegisterNoticePage(){
        return "admin-notice-add";
    }
    @PostMapping("/admin/add")
    public String registerNotice(){
        return "admin-notice-add";
    }
    @GetMapping("/admin/{noticeNo}/update")
    public String goToUpdateNoticePage(@PathVariable(name="noticeNo")Integer noticeNo, Model model){
        return "admins/notice/admins-notice-modify";
    }
    @PostMapping("/admin/{noticeNo}/update")
    public String updateNotice(@PathVariable(name= "noticeNo") Integer noticeNo, Model model){
        return "admins/notice/admins-notice-modify";
    }
    @PostMapping("/admin/{noticeNo}/delete")
    public String deleteNotice(@PathVariable(name= "noticeNo") Integer noticeNo, Model model){
        return "admins/notice";
    }
}
