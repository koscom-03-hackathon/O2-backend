package com.myinvestment.openai.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AIRequestDto {

    private String model;
    private List<Message> messages = new ArrayList<>();
    private int n;
    private double temperature;

    public AIRequestDto(String model, String prompt) {
        this.model = model;

        this.messages.add(Message.builder()
                .role("user")
                .content(prompt)
                .build());

        this.n = 10;
        this.temperature = 0.7;
    }

}
