package com.myinvestment.openai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AIStrategyPromptDto {

    private String date;
    private String strategy;
    private String reasoning;

}
