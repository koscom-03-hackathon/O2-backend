package com.myinvestment.diary.service;

import com.myinvestment.diary.dto.*;
import com.myinvestment.diary.repository.DiaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryMapper diaryMapper;

    public List<DiaryResponseDto> getAllDiaries(String date){
        return diaryMapper.getAllDiaries(date);
    }

    public DiaryResponseDto findDiaryById(int id){
        return diaryMapper.findDiaryById(id);
    }

    public DiaryPkResponseDto createDiary(DiaryCreationRequestDto dto){
        diaryMapper.createDiary(dto);
        return DiaryPkResponseDto.builder()
                .id(diaryMapper.findLatestDiary())
                .build();
    }

    public List<PortfolioResponseDto> getPortfolio(String userId){
        return diaryMapper.getPortfolio(userId);
    }

    public void modifyDiary(DiaryModifyRequestDto dto){
        diaryMapper.modifyDiary(dto);
    }

}
