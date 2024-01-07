package com.example.shoppingmall.review.repository;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.form.ReviewSearchForm;

import java.util.List;
import java.util.Map;

public interface ReviewRepository {
    void addReview(ReviewAddDTO reviewAddDTO);

    List<Review> findByItemNo(Long itemNo);

    Long getItemStockNo(Long memberOrderNo);

    String getItemSize(Long memberOrderDetailNo);

    String getMemberId(Long memberNo);

    void deleteReview(Long reviewNo);

    List<Review> findMReviewByPaging(int startPage, int pagePerReview, Long memberNo);

    Long countMemberReview(Long memberNo);

    List<Review> findReviewByPaging(Map<String, Integer> pagingSettings);

    Long countReview();

    String getItemName(Long itemNo);

    List<Review> searchReviewByPaging(ReviewSearchForm reviewSearchForm);

    Long countSearchedReview(ReviewSearchForm reviewSearchForm);

    Long getSumReviewScore(Long itemNo);

    Long countReviewByItemNo(Long itemNo);

    void updateItemGrade(float itemGrade,Long itemNo);

    Long getItemNoByReviewNo(Long reviewNo);

    List<Review> findReviewByItemNo(int startPage, int pagePerReview, Long itemNo);

    Long countItemReview(Long itemNo);

    Integer countReviewByMemberOrderDetailNo(Long memberOrderDetailNo);
}
