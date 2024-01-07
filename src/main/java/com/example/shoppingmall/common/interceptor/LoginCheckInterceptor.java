package com.example.shoppingmall.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        //미인증 사용자
        if(session.getAttribute("loginMember") == null && session.getAttribute("loginAdmin") == null){
            // 관리자 페이지로 리다이렉트
            if (isAdminPage(requestURI)) {
                log.warn("미인증 관리자 요청(비회원)");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);

            } else {
                //일반 사용자 페이지로 리다이렉트
                log.warn("미인증 사용자 요청");
                response.sendRedirect("/members/login");
            }
            return false;
        }
        return true;
    }

    private boolean isAdminPage(String requestURI) {
        // 관리자 페이지 판단 로직을 구현
        return requestURI.startsWith("/members/admin") ||
                requestURI.startsWith("/sales") ||
                requestURI.startsWith("/items/admin") ||
                requestURI.startsWith("/orders/admin") ||
                requestURI.startsWith("/notice/admin") ||
                requestURI.startsWith("/qna") ||
                requestURI.startsWith("/reviews");
    }

}
