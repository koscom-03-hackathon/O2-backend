package com.myinvestment.diary.service;

import com.myinvestment.diary.dto.*;
import com.myinvestment.diary.repository.DiaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryMapper diaryMapper;

    @Transactional
    public List<DiaryResponseDto> getAllDiaries(String date){
        return diaryMapper.getAllDiaries(date);
    }

    @Transactional
    public DiaryResponseDto findDiaryById(int id){
        return diaryMapper.findDiaryById(id);
    }

    @Transactional
    public DiaryPkResponseDto createDiary(DiaryCreationRequestDto dto){
        diaryMapper.createDiary(dto);
        return DiaryPkResponseDto.builder()
                .id(diaryMapper.findLatestDiary())
                .build();
    }

    @Transactional
    public List<PortfolioResponseDto> getPortfolio(PortfolioRequestDto dto){
        List<PortfolioResponseDto> portfolios = diaryMapper.getPortfolio(dto);

        for (PortfolioResponseDto portfolio : portfolios){
            portfolio.setRevenue(
                    (portfolio.getCurrentQuote() * portfolio.getQuantity())
                    - (portfolio.getPreviousQuote() * portfolio.getQuantity())
            );
            portfolio.setRatio(
                    ((double) portfolio.getCurrentQuote() - (double) portfolio.getPreviousQuote())
                    / (double) portfolio.getCurrentQuote() * 100
            );
        }

        return portfolios;
    }

    @Transactional
    public void modifyDiary(DiaryModifyRequestDto dto){
        diaryMapper.modifyDiary(dto);
    }

    @Transactional
    public void deleteDiary(DiaryDeletionRequestDto dto){
        diaryMapper.deleteDiary(dto);
    }

}
