package com.myinvestment.openai.service;

import com.myinvestment.openai.dto.*;
import com.myinvestment.openai.repository.DiaryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIService {

    private final DiaryMapper diaryMapper;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private HttpEntity<AIRequestDto> getHttpEntity(AIRequestDto chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + openaiApiKey);

        HttpEntity<AIRequestDto> httpRequest = new HttpEntity<>(chatRequest, headers);
        return httpRequest;
    }

    public AIResponseDto getAIFeedback(String userId){
        List<AIFeedbackPromptDto> feedbacks = diaryMapper.getAIFeedbacks(userId);

        StringBuilder prompt = new StringBuilder("다음은 한 투자자의 투자일기야. 여기서 투자일기란 일정 기간의 투자에 관한 피드백을 담은 일기야.");
        prompt.append("\n너는 투자일기를 쓴 투자자에게 조언을 주는 투자자문가야.");
        prompt.append("\n투자일기는 날짜와 피드백으로 구성되어 있어. 투자일기는 한개 혹은 여러개가 존재하는데, xml 태그를 사용해서 투자일기들의 날짜, 피드백을 구분해줄게.");

        for (AIFeedbackPromptDto feedback : feedbacks){
            prompt.append("\n<date>").append(feedback.getDate()).append("<date>");
            prompt.append("\n<feedback>").append(feedback.getFeedback()).append("<feedback>\n");
        }

        prompt.append("\n해당 투자일기들을 읽은 뒤, 투자일기를 바탕으로 최대한 구체적으로, 존댓말로 길게 20문장 이상으로 조언해줘.");

        AIRequestDto request = new AIRequestDto(model, prompt.toString());

        RestTemplate restTemplate = new RestTemplate();

        OpenaiResponseDto response = restTemplate.postForObject(apiUrl,
                getHttpEntity(request),
                OpenaiResponseDto.class);

        return AIResponseDto.builder()
                .response(response.getChoices().get(0).getMessage().getContent())
                .build();
    }

    public AIResponseDto getAIStrategy(String userId){
        List<AIStrategyPromptDto> strategies = diaryMapper.getAIStrategy(userId);

        StringBuilder prompt = new StringBuilder("다음은 한 투자자의 투자일기야. 여기서 투자일기란 어떤 시점의 투자 전략과 이유를 담은 일기야.");
        prompt.append("\n너는 투자일기를 쓴 투자자에게 조언을 주는 투자자문가야.");
        prompt.append("\n투자일기는 날짜와 투자 전략, 투자 이유로 구성되어 있어. 투자일기는 한개 혹은 여러개가 존재하는데, xml 태그를 사용해서 투자일기들의 날짜, 투자전략, 이유를 구분해줄게.");

        for (AIStrategyPromptDto strategy : strategies){
            prompt.append("\n<date>").append(strategy.getDate()).append("<date>");
            prompt.append("\n<strategy>").append(strategy.getStrategy()).append("<strategy>\n");
            prompt.append("\n<reasoning>").append(strategy.getReasoning()).append("<reasoning\n");
        }

        prompt.append("\n해당 투자일기들을 읽은 뒤, 투자일기를 바탕으로 최대한 구체적으로, 존댓말로 길게 20문장 이상으로 투자전략에 관해 조언해줘.");
        prompt.append("\n조언에는 구체적인 투자전략의 제시가 포함되어야 해.");

        AIRequestDto request = new AIRequestDto(model, prompt.toString());

        RestTemplate restTemplate = new RestTemplate();

        OpenaiResponseDto response = restTemplate.postForObject(apiUrl,
                getHttpEntity(request),
                OpenaiResponseDto.class);

        return AIResponseDto.builder()
                .response(response.getChoices().get(0).getMessage().getContent())
                .build();
    }
}
