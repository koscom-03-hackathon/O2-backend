package com.myinvestment.diary.dto;

import lombok.Getter;

@Getter
public class DiaryCreationRequestDto {

    String date;
    String title;
    String type;
    String content;
    String strategy;
    String reasoning;
    String feedback;

}
