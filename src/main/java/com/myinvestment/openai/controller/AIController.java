package com.myinvestment.openai.controller;

import com.myinvestment.openai.dto.AIResponseDto;
import com.myinvestment.openai.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AIController {

    private final AIService service;

    @GetMapping("/diary/feedback/openai")
    public AIResponseDto getAIFeedback(@RequestParam String userId){
        return service.getAIFeedback(userId);
    }

    @GetMapping("/diary/strategy/openai")
    public AIResponseDto getAIStrategy(@RequestParam String userId){
        return service.getAIStrategy(userId);
    }

}
