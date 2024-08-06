package com.myinvestment.openai.service;

import com.myinvestment.diary.dto.PortfolioResponseDto;
import com.myinvestment.diary.repository.DiaryMapper;
import com.myinvestment.openai.dto.*;
import com.myinvestment.openai.repository.AIMapper;
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

    private final AIMapper AiMapper;

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

    public AIResponseDto getAIFeedback(){
        List<AIFeedbackPromptDto> feedbacks = AiMapper.getAIFeedbacks();
        List<PortfolioDto> portfolios = AiMapper.getAllPortfolio();

        StringBuilder prompt = new StringBuilder("다음은 한 투자자의 투자일기야. 여기서 투자일기란 일정 기간의 투자에 관한 피드백을 담은 일기야.");
        prompt.append("\n너는 투자일기를 쓴 투자자에게 조언을 주는 투자자문가야. 청자가 이해하기 쉽도록 직관적으로 설명해줘.");
        prompt.append("\n투자일기는 날짜와 피드백으로 구성되어 있어. 투자일기는 한개 혹은 여러개가 존재하는데, xml 태그를 사용해서 투자일기들의 날짜, 피드백을 구분해줄게.");

        for (AIFeedbackPromptDto feedback : feedbacks){
            prompt.append("\n<date>").append(feedback.getDate()).append("</date>");
            prompt.append("\n<feedback>").append(feedback.getFeedback()).append("</feedback>\n");
        }

        prompt.append("\n 또한 다음은 각 날짜별로 투자자가 보유한 종목 포트폴리오 정보야. 포트폴리오 정보도 반영해줘. 다만, 무조건 반영하지 않아도 괜찮아.");
        prompt.append("\n각 종목의 정보 역시 xml 태그로 구분해줄게.");

        for (PortfolioDto portfolio : portfolios){
            prompt.append("<stock>").append(portfolio.getStock()).append("</stock>");
            prompt.append("<date>").append(portfolio.getDate()).append("</date>");
            prompt.append("<total_money_amount>").append(portfolio.getAmount()).append("</total_money_amount>");
            prompt.append("<quantity>").append(portfolio.getQuantity()).append("</quantity>");
            prompt.append("<average_price>").append(portfolio.getPreviousQuote()).append("</average_price>");
            prompt.append("<current_price>").append(portfolio.getCurrentQuote()).append("</current_price>");
            prompt.append("\n");
        }

        prompt.append("\n\n해당 투자일기들을 읽은 뒤, 투자일기를 바탕으로 최대한 구체적으로, 존댓말로 500자 이내로 조언해줘.");
        prompt.append("\n답변은 매매 결과 분석, 투자 전략 조언 카테고리로 나누어서 제공해줘. 각 카테고리를 제목으로 구분해서 글을 제공해줘.");
        prompt.append(("\n또한 구체적인 투자 전략을 포함해주고, 투자 전략 조언 카테고리는 시장 상황을 고려한 답변을 해줘."));
        prompt.append("\n네, 알겠습니다와 같은 대답은 생략하고, 매매 결과 분석부터 바로 시작해줘.");

        AIRequestDto request = new AIRequestDto(model, prompt.toString());

        RestTemplate restTemplate = new RestTemplate();

        OpenaiResponseDto response = restTemplate.postForObject(apiUrl,
                getHttpEntity(request),
                OpenaiResponseDto.class);

        return AIResponseDto.builder()
                .response(response.getChoices().get(0).getMessage().getContent())
                .build();
    }

    public AIResponseDto getAIStrategy(){
        List<AIStrategyPromptDto> strategies = AiMapper.getAIStrategy();
        List<PortfolioDto> portfolios = AiMapper.getAllPortfolio();

        StringBuilder prompt = new StringBuilder("다음은 한 투자자의 투자일기야. 여기서 투자일기란 어떤 시점의 투자 전략과 이유를 담은 일기야.");
        prompt.append("\n너는 투자일기를 쓴 투자자에게 조언을 주는 투자자문가야. 청자가 이해하기 쉽도록 직관적으로 설명해줘.");
        prompt.append("\n투자일기는 날짜와 투자 전략, 투자 근거로 구성되어 있어. 투자일기는 한개 혹은 여러개가 존재하는데, xml 태그를 사용해서 투자일기들의 날짜, 투자전략, 근거를 구분해줄게.\n");

        for (AIStrategyPromptDto strategy : strategies){
            prompt.append("\n<date>").append(strategy.getDate()).append("</date>");
            prompt.append("\n<strategy>").append(strategy.getStrategy()).append("</strategy>");
            prompt.append("\n<reasoning>").append(strategy.getReasoning()).append("</reasoning>\n");
        }

        prompt.append("\n 또한 다음은 각 날짜별로 투자자가 보유한 종목 포트폴리오 정보야. 포트폴리오 정보도 반영해줘. 다만, 무조건 반영하지 않아도 괜찮아.");
        prompt.append("\n각 종목의 정보 역시 xml 태그로 구분해줄게.");

        for (PortfolioDto portfolio : portfolios){
            prompt.append("<stock>").append(portfolio.getStock()).append("</stock>");
            prompt.append("<date>").append(portfolio.getDate()).append("</date>");
            prompt.append("<total_money_amount>").append(portfolio.getAmount()).append("</total_money_amount>");
            prompt.append("<quantity>").append(portfolio.getQuantity()).append("</quantity>");
            prompt.append("<average_price>").append(portfolio.getPreviousQuote()).append("</average_price>");
            prompt.append("<current_price>").append(portfolio.getCurrentQuote()).append("</current_price>");
            prompt.append("\n");
        }

        prompt.append("\n해당 투자일기들을 읽은 뒤, 투자일기를 바탕으로 최대한 구체적으로, 존댓말로 500자 이내로 조언해줘.");
        prompt.append("\n조언에는 구체적인 투자전략의 제시가 포함되어야 해.");
        prompt.append("\n답변은 매매 전략 분석, 투자 리스크 및 시장 전망, 조언 카테고리로 나누어서 제공해줘. 각 카테고리를 제목으로 구분해서 글을 제공해줘.");
        prompt.append("\n매매 전략 분석은 투자자가 쓴 투자일기의 매매 전략에 대한 분석이야.");
        prompt.append("\n네, 알겠습니다와 같은 대답은 생략하고, 매매 전략 분석부터 바로 시작해줘.");

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
