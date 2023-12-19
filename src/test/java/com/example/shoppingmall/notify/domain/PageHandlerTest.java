package com.example.shoppingmall.notify.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageHandlerTest {
    @Test
    public void test(){
        PageHandler ph = new PageHandler(250, 1);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.beginPage==1);
        assertTrue(ph.endPage==10);
    }
}