package com.example.shoppingmall.notify.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class PageHandler {
    int totalCount;//총 게시물 갯수
    int pageSize;//한 페이지에 출력되는 규모
    int naviSize;//네비게이션 페이징 규모
    int totalPage;//전체 출력되는 페이지의 갯수
    int page;//현재 페이지
    int beginPage;//네비의 페이징 첫번째페이지
    int endPage;//네비의 페이징 마지막페이지
    boolean showPrev;//이전
    boolean showNext;//다음

    public PageHandler(int totalCount, int page, int pageSize ){//totalPage계산하는데 필요
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;

        totalPage = (int)Math.ceil(totalCount/(double)pageSize);//반올림
        beginPage = page / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize -1, totalPage);//둘중 작은값을 endPage로
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;

    }
    public PageHandler(int totalCount, int page){//생성자 오버라이드 페이징수 지정용
        this(totalCount, page, 10);
    }
    //test용
//    void print(){
//        System.out.println("page = " + page);
//        System.out.println(showPrev ? "PREV" : "");
//        for (int i = beginPage; i<=endPage; i++){
//            System.out.print(i+" ");
//        }
//        System.out.println(showNext ? "Next":"");
//    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
