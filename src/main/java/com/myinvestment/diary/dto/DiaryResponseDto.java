package com.myinvestment.diary.dto;

import lombok.Getter;

@Getter
public class DiaryResponseDto {

    private Long id;
    private String date;
    private String title;
    private String type;
    private String content;
    private String strategy;
    private String reasoning;
    private String feedback;

}
