package com.example.shoppingmall;

import com.example.shoppingmall.common.interceptor.LoginCheckInterceptor;
import com.example.shoppingmall.common.interceptor.MemberCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add","/members/add-success",
                        "/admins/login",
                        "/members/login", "/members/logout",
                        "/items/all","/items/outer","/items/inner",
                        "/items/pants","/items/search","/items/categoricalSearch",
                        "/items/{itemNo}","/items/{itemNo}/qna","/items/{itemNo}/qna/{qnaNo}",
                        "/items/{itemNo}/reviews",
                        "/notice","/notice/{noticeNo}",
                        "/orders/check-itemstock","/orders/create","/orders/create",
                        "/orders/create-success","/orders/non-members","/orders/{orderNo}",
                        "/orders/{orderNo}", "/orders/delete-success",
                        "/carts","/carts","/carts/{cartNo}/update","/carts/{cartNo}/delete",
                        "/members/info", "/members/info/id", "/members/info/id/find-success",
                        "/members/info/pw", "/members/info/pw/update", "/members/info/pw/update","/members/info/pw/update-success",
                        "/css/**", "/*.ico", "/error","/images/**","/js/**");
        registry.addInterceptor(new MemberCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add","/members/add-success",
                        "/admins/login",
                        "/members/login","/members/logout",
                        "/members/delete-success","/members/{memberNo}/orders/check-itemstock","/members/orders/create",
                        "/members/{memberNo}/orders/create-order","members/{memberNo}/orders/create-success",
                        "/members/{memberNo}/orders/create-success",
                        "/items/all","/items/outer","/items/inner",
                        "/items/pants","/items/search","/items/categoricalSearch",
                        "/items/{itemNo}","/items/{itemNo}/qna","/items/{itemNo}/qna/{qnaNo}",
                        "/items/{itemNo}/reviews",
                        "/items/{itemNo}/qna/{qnaNo}","/members/{memberNo}/qna/{qnaNo}/update",
                        "/qna/{memberNo}/{itemNo}","/members/{memberNo}/qna/{qnaNo}/update",
                        "/items/{itemNo}/reviews","/reviews/{memberNo}/{itemNo}/add","/reviews/{memberNo}/{reviewNo}",
                        "/notice","/notice/{noticeNo}",
                        "/orders/check-itemstock","/orders/create","/orders/create",
                        "/orders/create-success","/orders/non-members","/orders/{orderNo}",
                        "/orders/{orderNo}", "/orders/delete-success",
                        "/css/**", "/*.ico", "/error","/images/**","/js/**");
    }

}

