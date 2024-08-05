package com.myinvestment.openai.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenaiResponseDto {

    private List<Choice> choices;

    @Data
    @RequiredArgsConstructor
//    @NoArgsConstructor
    public static class Choice{
        private int index;
        private Message message;
    }

}
