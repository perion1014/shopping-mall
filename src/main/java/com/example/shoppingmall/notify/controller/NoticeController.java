package com.example.shoppingmall.notify.controller;

import com.example.shoppingmall.notify.dto.NoticeAddDTO;
import com.example.shoppingmall.notify.dto.NoticeDeleteDTO;
import com.example.shoppingmall.notify.dto.NoticeListDTO;
import com.example.shoppingmall.notify.dto.NoticeUpdateDTO;
import com.example.shoppingmall.notify.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/notice")//절대경로
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /*공지사항 리스트 출력*/
    @GetMapping("/admin")
    public String showNoticeList(Model model){
        model.addAttribute("noticeListDTOList", noticeService.findAllNotice());
        return "admins/notice/admins-notice";
    }
    /*공지사항 디테일 Admin전용*/
    @GetMapping("/admin/{noticeNo}")
    public String showNoticeDetail(@PathVariable(name="noticeNo")Long noticeNo, Model model){
        model.addAttribute("noticeUpdateDTO", noticeService.getNoticeInfo(noticeNo));
        return "admins/notice/admins-notice-detail";
    }
    /*공지사항 작성 페이지 이동 Admin전용*/
    @GetMapping("/admin/add")
    public String goToRegisterNoticePage(){
        //다른작업을하다가 등록을 누르면 에러가 뜨는 오류가 있음!!!
        return "admins/notice/admins-notice-add";
    }
    /*공지사항 작성 실행 Admin전용*/
    @PostMapping("/admin/add")
    public String registerNotice(@RequestParam("adminNo") Integer adminNo, @ModelAttribute NoticeAddDTO noticeAddDTO){
        //adminId도 가져와야함 @RequestParam("adminId") String adminId
        System.out.println("adminNo = " + adminNo);
        //추가구현
        //해당 작성자admin아이디 출력
        //작성일자 생성 셀렉트문으로 DB에 있는 글들을 끌어올떄는 Timestamp DATE_ADD(NOW(), INTERVAL 0 HOUR)
        //9시간 차이는 INTERVAL -9 HOUR

        noticeService.addNotice(adminNo, noticeAddDTO);
        return "redirect:/notice/admin";
    }
    /*공지사항 수정 페이지 Admin전용*///공지 리스트에서 해당 게시물 클릭하면 수정페이지가 나오고 작성된 내용 그대로 출력
    @GetMapping("/admin/{noticeNo}/update")
    public String goToUpdateNoticePage(@PathVariable(name="noticeNo")Long noticeNo, Model model){
        model.addAttribute("noticeUpdateDTO", noticeService.getNoticeInfo(noticeNo));
        return "admins/notice/admins-notice-modify";
    }
    /*공지사항 수정 실행 Admin전용*/
    @PostMapping("/admin/{noticeNo}/update")
    public String updateNotice(@ModelAttribute NoticeUpdateDTO noticeUpdateDTO){
        noticeService.update(noticeUpdateDTO);
        return "admins/notice/admins-notice-modify";
//        return "redirect:/notice/admin/{noticeNo}";
    }
    /*공지사항 삭제 Admin전용*/
    @PostMapping("/admin/{noticeNo}/delete")
    public String deleteNotice(@PathVariable(name= "noticeNo") Long noticeNo){
        noticeService.deleteNotice(noticeNo);
        return "redirect:/notice/admin";
    }
}
