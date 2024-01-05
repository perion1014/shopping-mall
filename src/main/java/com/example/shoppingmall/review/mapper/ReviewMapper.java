package com.example.shoppingmall.review.mapper;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.form.ReviewSearchForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
    void addReview(ReviewAddDTO reviewAddDTO);

    List<Review> findByItemNo(Long itemNo);

    Long getItemStockNo(Long memberOrderNo);

    String getItemSize(Long memberOrderDetailNo);

    String getMemberId(Long memberNo);

    void deleteReview(Long reviewNo);

    List<Review> findMReviewByPaging(@Param("startPage")int startPage,
                             @Param("pagePerReview") int pagePerReview,
                             @Param("memberNo") Long memberNo);

    Long countMemberReview(Long memberNo);

    List<Review> findReviewByPaging(Map<String, Integer> pagingSettings);

    Long countReview();

    String getItemName(Long itemNo);

    List<Review> searchReviewByPaging(ReviewSearchForm reviewSearchForm);

    Long countSearchedReview(ReviewSearchForm reviewSearchForm);

    Long getSumReviewScore(Long itemNo);

    Long countReviewByItemNo(Long itemNo);

    void updateItemGrade(@Param("itemGrade")float itemGrade,
                         @Param("itemNo") Long itemNo);

    Long getItemNoByReviewNo(Long reviewNo);
}
