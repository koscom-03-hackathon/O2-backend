package com.myinvestment.diary.controller;

import com.myinvestment.diary.dto.*;
import com.myinvestment.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/diary")
    public List<DiaryResponseDto> getAllDiaries(@RequestParam String date){
        return diaryService.getAllDiaries(date);
    }

    @GetMapping("/diary/{id}")
    public DiaryResponseDto findDiaryById(@PathVariable int id){
        return diaryService.findDiaryById(id);
    }

    @PostMapping("/diary/new")
    public DiaryPkResponseDto createDiary(@RequestBody DiaryCreationRequestDto dto){
        return diaryService.createDiary(dto);
    }

    @GetMapping("/diary/portfolio")
    public List<PortfolioResponseDto> getPortfolio(@RequestParam String userID){
        return diaryService.getPortfolio(userID);
    }

    @PostMapping("/diary/update")
    public void modifyDiary(@RequestBody DiaryModifyRequestDto dto){
        diaryService.modifyDiary(dto);
    }

}
