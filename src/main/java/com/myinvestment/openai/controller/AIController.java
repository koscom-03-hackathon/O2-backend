package com.myinvestment.openai.controller;

import com.myinvestment.openai.dto.AIResponseDto;
import com.myinvestment.openai.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AIController {

    private final AIService service;

    @GetMapping("/diary/feedback/openai")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public AIResponseDto getAIFeedback(){
        return service.getAIFeedback();
    }

    @GetMapping("/diary/strategy/openai")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public AIResponseDto getAIStrategy(){
        return service.getAIStrategy();
    }

}
