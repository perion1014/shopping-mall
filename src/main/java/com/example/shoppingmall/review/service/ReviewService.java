package com.example.shoppingmall.review.service;

import com.example.shoppingmall.review.domain.Review;
import com.example.shoppingmall.review.dto.ReviewAddDTO;
import com.example.shoppingmall.review.dto.ReviewDTO;
import com.example.shoppingmall.review.form.ReviewPageForm;
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

            Long memberOrderNo = review.getMemberOrderNo();
            Long itemStockNo = reviewRepository.getItemStockNo(memberOrderNo);

            String itemSize = reviewRepository.getItemSize(itemStockNo);
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

        int startPage = (page-1) * 12;
        int pagePerReview = 12;

        List<Review> reviews = reviewRepository.findMReviewByPaging(startPage,pagePerReview,memberNo);

        List<ReviewDTO> resultList = new ArrayList<>();

        for (Review review : reviews) {

            Long memberOrderNo = review.getMemberOrderNo();
            Long itemStockNo = reviewRepository.getItemStockNo(memberOrderNo);

            String itemSize = reviewRepository.getItemSize(itemStockNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            resultList.add(reivewToReviewDTO(review,itemSize,memberId));

        }

        return resultList;
    }

    public ReviewPageForm setReviewPageByMember(int page,Long memberNo) {

        int pagePerQna = 12;
        int pageLimit = 10;

        Long qnaCount = reviewRepository.countMemberReview(memberNo);

        int totalPage = (int) (Math.ceil((double) qnaCount / pagePerQna));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ReviewPageForm(page,totalPage,startPage,endPage);
    }

    // 어드민 리뷰 페이징

    // 마이페이지 리뷰 가져오고 페이징
    public List<ReviewDTO> getAllReviewList(int page) {

        int startPage = (page-1) * 12;
        int pagePerReview = 12;

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("pagePerReview", pagePerReview);

        List<Review> reviews = reviewRepository.findReviewByPaging(pagingSettings);

        List<ReviewDTO> resultList = new ArrayList<>();

        for (Review review : reviews) {

            Long memberOrderNo = review.getMemberOrderNo();
            Long itemStockNo = reviewRepository.getItemStockNo(memberOrderNo);

            String itemSize = reviewRepository.getItemSize(itemStockNo);
            String memberId = reviewRepository.getMemberId(review.getMemberNo());

            resultList.add(reivewToReviewDTO(review,itemSize,memberId));

        }

        return resultList;
    }

    public ReviewPageForm setReviewPage(int page) {

        int pagePerQna = 12;
        int pageLimit = 10;

        Long qnaCount = reviewRepository.countReview();

        int totalPage = (int) (Math.ceil((double) qnaCount / pagePerQna));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new ReviewPageForm(page,totalPage,startPage,endPage);
    }

}
