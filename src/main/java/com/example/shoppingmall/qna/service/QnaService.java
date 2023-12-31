package com.example.shoppingmall.qna.service;

import com.example.shoppingmall.member.domain.Member;
import com.example.shoppingmall.member.dto.MemberSearchDTO;
import com.example.shoppingmall.member.form.MemberPageForm;
import com.example.shoppingmall.member.form.MemberSearchForm;
import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.qna.dto.QnaAddDTO;
import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.dto.QnaUpdateDTO;
import com.example.shoppingmall.qna.form.QnaPageForm;
import com.example.shoppingmall.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;


@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;

    @Transactional
    public List<QnaDTO> getQnaByItemNo(Long itemNo) {

        // 출력할 QnaDTO List 만듬
        List<QnaDTO> QnaDTOList = new ArrayList<>();

        //  itemNo을 이용해서 QnA 정보들을 가져오고 List에 담음
        List<Qna> qnaList= qnaRepository.findByItemNo(itemNo);

        // 가져온 QnA 정보들 중에 memberNo를 가져와서 이를 이용하여 memberId를 가져오고 DTO에 담기
        for (Qna qna : qnaList) {
            Long memberNo = qna.getMemberNo();

            qna.setItemNo(itemNo);

            String memberId = qnaRepository.getMemberIdByNo(memberNo);

            QnaDTOList.add(QnaDTO.qnaToQnaDTO(qna, memberId));

        }

        return QnaDTOList;
    }

    @Transactional
    public void addQna(Long itemNo, Long memberNo, QnaAddDTO qnaAddDTO) {
        qnaRepository.addQna(QnaAddDTO.QnaAddDTOToQna(memberNo,itemNo,qnaAddDTO));
    }


    @Transactional(readOnly = true)
    public List<QnaDTO> getQnaListPage(int page) {

        int startPage = (page-1) * 12; //시작 페이지
        int pagePerQna = 12; // 멤버 수

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("pagePerQna", pagePerQna);
        List<Qna> qnaList = qnaRepository.findAllByPaging(pagingSettings);


        List<QnaDTO> resultList = new ArrayList<>();

        for(Qna qna : qnaList){

            Long memberNo = qna.getMemberNo();
            String memberId = qnaRepository.getMemberIdByNo(memberNo);

            resultList.add(QnaDTO.fromEntity(qna,memberId));
        }

        return resultList;
    }

    @Transactional(readOnly = true)
    public QnaPageForm setQnaListPage(int page) {

        int pagePerQna = 12; // 보여줄 멤버 수
        int pageLimit = 10; // 하단 페이징 번호 갯수

        Long qnaCount = qnaRepository.countAll();

        int totalPage = (int) (Math.ceil((double) qnaCount / pagePerQna));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new QnaPageForm(page,totalPage,startPage,endPage);
    }

    public QnaDTO getQnaInfo(Long qnaNo) {

        QnaDTO qnaDetail = new QnaDTO();

        Qna qna = qnaRepository.findByQnaNo(qnaNo);

        Long memberNo = qna.getMemberNo();
        String memberId = qnaRepository.getMemberIdByNo(memberNo);

        qnaDetail = QnaDTO.fromEntity(qna,memberId);

        return qnaDetail;
    }

    public void replyQna(Long qnaNo, String qnaAnswer) {

        Qna answer = new Qna();

        answer.setQnaNo(qnaNo);
        answer.setQnaAnswer(qnaAnswer);

        qnaRepository.replyQna(answer);
    }

    public void deleteAnswer(Long qnaNo) {

        qnaRepository.deleteAnswer(qnaNo);
    }

    @Transactional(readOnly = true)
    public List<QnaDTO> getQnaListByItemNo(int page, Long itemNo) {

        int startPage = (page-1) * 12; //시작 페이지
        int pagePerQna = 12; // 멤버 수


        List<Qna> qnaList = qnaRepository.findQnaByPaging(startPage,pagePerQna,itemNo);


        List<QnaDTO> resultList = new ArrayList<>();

        for(Qna qna : qnaList){

            Long memberNo = qna.getMemberNo();
            String memberId = qnaRepository.getMemberIdByNo(memberNo);

            resultList.add(QnaDTO.fromEntity(qna,memberId));
        }

        return resultList;
    }

    @Transactional(readOnly = true)
    public QnaPageForm setQnaListPageByItemNo(int page, Long itemNo) {

        int pagePerQna = 12; // 보여줄 멤버 수
        int pageLimit = 10; // 하단 페이징 번호 갯수

        Long qnaCount = qnaRepository.countQnaByitemNo(itemNo);

        int totalPage = (int) (Math.ceil((double) qnaCount / pagePerQna));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new QnaPageForm(page,totalPage,startPage,endPage);
    }

    public QnaPageForm setMQnaListPage(int page, Long memberNo) {
        int pagePerQna = 12; // 보여줄 멤버 수
        int pageLimit = 10; // 하단 페이징 번호 갯수

        Long qnaCount = qnaRepository.countMemberQna(memberNo);

        int totalPage = (int) (Math.ceil((double) qnaCount / pagePerQna));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new QnaPageForm(page,totalPage,startPage,endPage);
    }

    public List<QnaDTO> getMQnaListPage(int page, Long memberNo) {

        int startPage = (page-1) * 12;
        int pagePerQna = 12;


        List<Qna> qnaList = qnaRepository.findMQnaByPaging(startPage,pagePerQna,memberNo);


        List<QnaDTO> resultList = new ArrayList<>();

        for(Qna qna : qnaList){
            QnaDTO qnaDTO;

            String itemName = qnaRepository.getItemName(qna.getItemNo());

            Long memberNoForId = qna.getMemberNo();
            String memberId = qnaRepository.getMemberIdByNo(memberNoForId);

            qnaDTO = QnaDTO.fromEntity(qna,memberId);
            qnaDTO.setItemName(itemName);

            resultList.add(qnaDTO);
        }

        return resultList;
    }

    public void modifyQna(Long qnaNo, QnaUpdateDTO qnaUpdateDTO) {

        qnaUpdateDTO.setQnaNo(qnaNo);

        qnaRepository.updateQna(qnaUpdateDTO);
    }

    public void deleteQustion(Long qnaNo) {
        qnaRepository.deleteQna(qnaNo);
    }


    // 답변완료 Q&A 페이징
    public List<QnaDTO> getQnaAnswered(int page) {
        int startPage = (page-1) * 12; //시작 페이지
        int pagePerQna = 12; // 멤버 수

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("pagePerQna", pagePerQna);

        List<Qna> qnaList = qnaRepository.findQnaAnswered(pagingSettings);


        List<QnaDTO> resultList = new ArrayList<>();

        for(Qna qna : qnaList){

            Long memberNo = qna.getMemberNo();
            String memberId = qnaRepository.getMemberIdByNo(memberNo);

            resultList.add(QnaDTO.fromEntity(qna,memberId));
        }

        return resultList;
    }

    public QnaPageForm setQnaPageAnswered(int page) {
        int pagePerQna = 12; // 보여줄 멤버 수
        int pageLimit = 10; // 하단 페이징 번호 갯수

        Long qnaCount = qnaRepository.countQnaAnswered();

        int totalPage = (int) (Math.ceil((double) qnaCount / pagePerQna));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new QnaPageForm(page,totalPage,startPage,endPage);
    }

    // 답변대기 Q&A 페이징
    public List<QnaDTO> getUnansweredQna(int page) {
        int startPage = (page-1) * 12; //시작 페이지
        int pagePerQna = 12; // 멤버 수

        Map<String, Integer> pagingSettings = new HashMap<>();
        pagingSettings.put("startPage", startPage);
        pagingSettings.put("pagePerQna", pagePerQna);

        List<Qna> qnaList = qnaRepository.findUnansweredQna(pagingSettings);


        List<QnaDTO> resultList = new ArrayList<>();

        for(Qna qna : qnaList){

            Long memberNo = qna.getMemberNo();
            String memberId = qnaRepository.getMemberIdByNo(memberNo);

            resultList.add(QnaDTO.fromEntity(qna,memberId));
        }

        return resultList;
    }

    public QnaPageForm setUnansweredQnaPage(int page) {
        int pagePerQna = 12; // 보여줄 멤버 수
        int pageLimit = 10; // 하단 페이징 번호 갯수

        Long qnaCount = qnaRepository.countUnansweredQna();

        int totalPage = (int) (Math.ceil((double) qnaCount / pagePerQna));

        int startPage = (((int)(Math.ceil((double) page / pageLimit))) - 1) * pageLimit + 1;

        int endPage = startPage + pageLimit - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return new QnaPageForm(page,totalPage,startPage,endPage);
    }

    public String getItemName(long itemNo) {
        return qnaRepository.getItemName(itemNo);
    }
}
