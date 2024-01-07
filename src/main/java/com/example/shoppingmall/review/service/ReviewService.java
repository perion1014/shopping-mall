package com.example.shoppingmall.review.service;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.dto.ReviewDTO;
import com.example.shoppingmall.review.form.ReviewPageForm;
import com.example.shoppingmall.review.form.ReviewSearchForm;
import com.example.shoppingmall.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.shoppingmall.review.dto.ReviewDTO.reivewToReviewDTO;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    // 리뷰 등록
    public void addReview(ReviewAddDTO reviewAddDTO) {
        reviewRepository.addReview(reviewAddDTO);
    }

    public List<ReviewDTO> getReviewListByitemNo(Long itemNo) {

        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        List<Review> reviews = reviewRepository.findByItemNo(itemNo);

        for (Review review : reviews) {

            Long memberOrderDetailNo = review.getMemberOrderDetailNo();

//            Long itemStockNo = reviewRepository.getItemStockNo(memberOrderNo);

            String itemSize = reviewRepository.getItemSize(memberOrderDetailNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            reviewDTOList.add(reivewToReviewDTO(review,itemSize,memberId));

        }

        return reviewDTOList;
    }

    public void deleteReview(Long reviewNo) {
        reviewRepository.deleteReview(reviewNo);
    }

    // 마이페이지 리뷰 가져오고 페이징
    public List<ReviewDTO> getReviewListByMember(Long memberNo, int page) {

        int startPage = (page-1) * 10;
        int pagePerReview = 10;

        List<Review> reviews = reviewRepository.findMReviewByPaging(startPage,pagePerReview,memberNo);

        List<ReviewDTO> resultList = new ArrayList<>();

        for (Review review : reviews) {

            Long memberOrderDetailNo = review.getMemberOrderDetailNo();

            String ItemName = reviewRepository.getItemName(review.getItemNo());

            String itemSize = reviewRepository.getItemSize(memberOrderDetailNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            ReviewDTO reviewDTO = reivewToReviewDTO(review,itemSize,memberId);

            reviewDTO.setItemName(ItemName);
            resultList.add(reviewDTO);

        }

        return resultList;
    }

    public ReviewPageForm setReviewPageByMember(int page,Long memberNo) {

        int pagePerReview = 10;
        int pageLimit = 10;

        Long reviewCount = reviewRepository.countMemberReview(memberNo);

        int totalPage = (int) (Math.ceil((double) reviewCount / pagePerReview));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ReviewPageForm(page,totalPage,startPage,endPage);
    }

    // 어드민 리뷰 페이징


    public List<ReviewDTO> getAllReviewList(int page) {

        int startPage = (page-1) * 7;
        int pagePerReview = 7;

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("pagePerReview", pagePerReview);

        List<Review> reviews = reviewRepository.findReviewByPaging(pagingSettings);

        List<ReviewDTO> resultList = new ArrayList<>();

        for (Review review : reviews) {

            Long memberOrderDetailNo = review.getMemberOrderDetailNo();

            String ItemName = reviewRepository.getItemName(review.getItemNo());
//            Long itemStockNo = reviewRepository.getItemStockNo(memberOrderNo);

            String itemSize = reviewRepository.getItemSize(memberOrderDetailNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            ReviewDTO reviewDTO = reivewToReviewDTO(review,itemSize,memberId);

            reviewDTO.setItemName(ItemName);
            resultList.add(reviewDTO);

        }

        return resultList;
    }

    public ReviewPageForm setReviewPage(int page) {

        int pagePerReview = 7;
        int pageLimit = 10;

        Long reviewCount = reviewRepository.countReview();

        int totalPage = (int) (Math.ceil((double) reviewCount / pagePerReview));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ReviewPageForm(page,totalPage,startPage,endPage);
    }


    // 어드민 검색 페이징
    public List<ReviewDTO> getSearchedReviewList(int page, ReviewSearchForm reviewSearchForm) {

        int startPage = (page-1) * 7;
        int perPageReview = 7;


        reviewSearchForm.setStartPage(startPage);
        reviewSearchForm.setPerPageReview(perPageReview);

        List<Review> reviews = reviewRepository.searchReviewByPaging(reviewSearchForm);

        List<ReviewDTO> resultList = new ArrayList<>();

        for (Review review : reviews) {

            Long memberOrderDetailNo = review.getMemberOrderDetailNo();

            String ItemName = reviewRepository.getItemName(review.getItemNo());

            String itemSize = reviewRepository.getItemSize(memberOrderDetailNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            ReviewDTO reviewDTO = reivewToReviewDTO(review,itemSize,memberId);


            reviewDTO.setItemName(ItemName);
            resultList.add(reviewDTO);

        }

        return resultList;
    }

    public ReviewPageForm setSearchedReviewPage(int page, ReviewSearchForm reviewSearchForm) {

        int pagePerReview = 7;
        int pageLimit = 10;

        Long reviewCount = reviewRepository.countSearchedReview(reviewSearchForm);

        int totalPage = (int) (Math.ceil((double) reviewCount / pagePerReview));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ReviewPageForm(page,totalPage,startPage,endPage);
    }

    public void updateItemGrade(Long itemNo) {

        float itemGrade = 0;

        Long reviewScoreTotal = reviewRepository.getSumReviewScore(itemNo);

        Long reviewCount = reviewRepository.countReviewByItemNo(itemNo);

        if (reviewScoreTotal !=  null && reviewCount != null ) {
            float result = (float) reviewScoreTotal / reviewCount ;

            itemGrade = (float) (Math.round(result * 10.0) / 10.0);
        }


        reviewRepository.updateItemGrade(itemGrade,itemNo);
    }


    public Long getItemNoByReviewNo(Long reviewNo) {
        return reviewRepository.getItemNoByReviewNo(reviewNo);
    }

    // 아이템 관련 더보기
    // 마이페이지 리뷰 가져오고 페이징
    public List<ReviewDTO> getReviewListByItemNo(int page, Long itemNo) {

        int startPage = (page-1) * 10;
        int pagePerReview = 10;

        List<Review> reviews = reviewRepository.findReviewByItemNo(startPage,pagePerReview,itemNo);

        List<ReviewDTO> resultList = new ArrayList<>();

        for (Review review : reviews) {

            Long memberOrderDetailNo = review.getMemberOrderDetailNo();

            String ItemName = reviewRepository.getItemName(review.getItemNo());

            String itemSize = reviewRepository.getItemSize(memberOrderDetailNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            ReviewDTO reviewDTO = reivewToReviewDTO(review,itemSize,memberId);

            reviewDTO.setItemName(ItemName);
            resultList.add(reviewDTO);

        }

        return resultList;
    }

    public ReviewPageForm setReviewPageByItemNo(int page,Long itemNo) {

        int pagePerReview = 10;
        int pageLimit = 10;

        Long reviewCount = reviewRepository.countItemReview(itemNo);

        int totalPage = (int) (Math.ceil((double) reviewCount / pagePerReview));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ReviewPageForm(page,totalPage,startPage,endPage);
    }

    public String getItemName(Long itemNo) {
        return reviewRepository.getItemName(itemNo);
    }
}
