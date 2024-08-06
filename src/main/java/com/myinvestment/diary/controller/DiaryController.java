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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<DiaryResponseDto> getAllDiaries(@RequestParam String date){
        return diaryService.getAllDiaries(date);
    }

    @GetMapping("/diary/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public DiaryResponseDto findDiaryById(@PathVariable int id){
        return diaryService.findDiaryById(id);
    }

    @PostMapping("/diary/new")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public DiaryPkResponseDto createDiary(@RequestBody DiaryCreationRequestDto dto){
        return diaryService.createDiary(dto);
    }

    @GetMapping("/diary/portfolio")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<PortfolioResponseDto> getPortfolio(@RequestParam String userId, @RequestParam String date){
        return diaryService.getPortfolio(
                PortfolioRequestDto.builder()
                        .date(date)
                        .build()
        );
    }

    @PostMapping("/diary/update")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void modifyDiary(@RequestBody DiaryModifyRequestDto dto){
        diaryService.modifyDiary(dto);
    }

    @DeleteMapping("/diary/delete")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void deleteDiary(@RequestBody DiaryDeletionRequestDto dto){
        diaryService.deleteDiary(dto);
    }

}
