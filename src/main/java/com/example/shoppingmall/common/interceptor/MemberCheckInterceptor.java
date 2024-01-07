package com.example.shoppingmall.common.interceptor;

import com.example.shoppingmall.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class MemberCheckInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Object handler) throws Exception {
//
//        HttpSession session = request.getSession();
//        Member member =  (Member)session.getAttribute("loginMember");
//
//        System.out.println("MemberCheckInterceptor preHandle: " + request.getRequestURI());
//
//        Long currentNoLong = member.getMemberNo();
//
//        String currentNo = Long.toString(currentNoLong);
//        String requestedMemberNo = null;
//
//        String requestURI = request.getRequestURI();
//        String[] path = requestURI.split("/");
//
//        log.info("멤버 체크 인터셉터 실행 {}",requestURI);
//
//        //URI 경로에서 식별자 추출
//        if(path.length > 2 && path[1].equals("members")){
//            requestedMemberNo = path[2];
//        }
//
//        //현재 사용자의 회원번호와 요청된 멤버 회원번호가 다르면 정보 차단
//        if(requestedMemberNo!=null && !currentNo.equals(requestedMemberNo)){
//            log.warn("{}번 회원으로부터 {}번 회원의 정보에 잘못된 접근을 요청", currentNo,requestedMemberNo);
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return false;
//        }
//
//
//        return true;
//    }
}
