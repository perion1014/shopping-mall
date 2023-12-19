package com.example.shoppingmall.notify.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")//절대경로
@RequiredArgsConstructor
public class NoticeController {

    @GetMapping("/admin")
    public String showNoticeList(){
        return "admins/notice/admins-notice";
    }
    @GetMapping("/admin/{noticeNo}")
    public String showNoticeDetail(){
        return "admins/notice/admins-notice-detail";
    }
    @GetMapping("/admin/add")
    public String goToRegisterNoticePage(){
        return "admins/notice/admins-notice-add";
    }
    @PostMapping("/admin/add")
    public String registerNotice(){
        return "admins/notice/admins-notice-add";
    }
    @GetMapping("/admin/{noticeNo}/update")
    public String goToUpdateNoticePage(){
        return "admins/notice/admins-notice-modify";
    }
    @PostMapping("/admin/{noticeNo}/update")
    public String updateNotice(){
        return "admins/notice/admins-notice-modify";
    }
    @PostMapping("/admin/{noticeNo}/delete")
    public String deleteNotice(){
        return "admins/notice/admins";
    }
}
