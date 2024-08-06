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
    @CrossOrigin("*")
    public List<DiaryResponseDto> getAllDiaries(@RequestParam String date){
        return diaryService.getAllDiaries(date);
    }

    @GetMapping("/diary/{id}")
    @CrossOrigin("*")
    public DiaryResponseDto findDiaryById(@PathVariable int id){
        return diaryService.findDiaryById(id);
    }

    @PostMapping("/diary/new")
    @CrossOrigin("*")
    public DiaryPkResponseDto createDiary(@RequestBody DiaryCreationRequestDto dto){
        return diaryService.createDiary(dto);
    }

    @GetMapping("/diary/portfolio")
    @CrossOrigin("*")
    public List<PortfolioResponseDto> getPortfolio(@RequestParam String userID){
        return diaryService.getPortfolio(userID);
    }

    @PostMapping("/diary/update")
    @CrossOrigin("*")
    public void modifyDiary(@RequestBody DiaryModifyRequestDto dto){
        diaryService.modifyDiary(dto);
    }

}
