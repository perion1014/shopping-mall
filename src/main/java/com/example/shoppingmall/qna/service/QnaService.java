package com.example.shoppingmall.qna.service;

import com.example.shoppingmall.qna.domain.Qna;
import com.example.shoppingmall.qna.dto.QnaAddDTO;
import com.example.shoppingmall.qna.dto.QnaDTO;
import com.example.shoppingmall.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



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

            String memberId = qnaRepository.getMemberIdByNo(memberNo);

            QnaDTOList.add(QnaDTO.qnaToQnaDTO(qna, memberId));

        }

        return QnaDTOList;
    }

    @Transactional
    public void addQna(Long itemNo, Long memberNo, QnaAddDTO qnaAddDTO) {
        qnaRepository.addQna(QnaAddDTO.QnaAddDTOToQna(memberNo,itemNo,qnaAddDTO));
    }
}
