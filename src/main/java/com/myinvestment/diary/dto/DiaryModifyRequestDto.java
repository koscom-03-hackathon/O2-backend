package com.myinvestment.diary.dto;

import lombok.Getter;

@Getter
public class DiaryModifyRequestDto {

    int id;
    String date;
    String title;
    String content;
    String type;
    String strategy;
    String reasoning;
    String feedback;

}
