package com.myinvestment.openai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AIFeedbackPromptDto {
    String date;
    String content;
    String feedback;
}
