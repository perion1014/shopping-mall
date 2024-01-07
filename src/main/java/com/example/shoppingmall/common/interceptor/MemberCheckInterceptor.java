package com.example.shoppingmall.common.interceptor;

import com.example.shoppingmall.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class MemberCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);
        String requestedMemberNo = null;

        System.out.println("requestURI = " + requestURI);
        Member member = (Member) session.getAttribute("loginMember");
        System.out.println("member = " + member);
        
        if (member != null) {

            Long currentNoLong = member.getMemberNo();
            String currentNo = Long.toString(currentNoLong);

            String[] path = requestURI.split("/");

            if(path.length > 2 && path[1].equals("members")){
                requestedMemberNo = path[2];
            }

            log.info("멤버 체크 인터셉터 실행 {}", requestURI);

            //현재 사용자의 회원번호와 요청된 멤버 회원번호가 다르면 정보 차단
            if (!currentNo.equals(requestedMemberNo)) {
                log.warn("{}번 회원으로부터 {}번 회원의 정보에 잘못된 접근을 요청", currentNo, requestedMemberNo);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return false;
            }

            //회원이 관리자 권한을 요청하는 경우 차단
            if (isAdminPage(requestURI)) {
                log.warn("{}번 회원으로부터 잘못된 관리자 권한 요청", currentNo);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return false;
                }
            }
            return true;
          }

        private boolean isAdminPage(String requestURI) {
            // 관리자 페이지 판단 로직을 구현
            return requestURI.startsWith("/members/admin") ||
                    requestURI.startsWith("/sales") ||
                    requestURI.startsWith("/items/ad") ||
                    requestURI.startsWith("/items/admin/") ||
                    requestURI.startsWith("/orders/admin") ||
                    requestURI.startsWith("/orders/admin/") ||
                    requestURI.startsWith("/notice/ad") ||
                    requestURI.startsWith("/notice/admin/") ||
                    requestURI.startsWith("/qna") ||
                    requestURI.startsWith("/reviews");
        }

    }



