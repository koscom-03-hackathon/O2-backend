package com.myinvestment.diary.repository;

import com.myinvestment.diary.dto.*;
import com.myinvestment.exception.MapperException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    List<DiaryResponseDto> getAllDiaries(String date) throws MapperException;
    DiaryResponseDto findDiaryById(int id) throws MapperException;
    void createDiary(DiaryCreationRequestDto diaryCreationRequestDto) throws MapperException;
    int findLatestDiary() throws MapperException;
    List<PortfolioResponseDto> getPortfolio(String userId) throws MapperException;
    void modifyDiary(DiaryModifyRequestDto diaryModifyRequestDto) throws MapperException;

}
