package com.myinvestment.openai.repository;

import com.myinvestment.openai.dto.AIFeedbackPromptDto;
import com.myinvestment.exception.MapperException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DiaryMapperTest {
    DiaryMapper mapper = new DiaryMapper() {
        @Override
        public List<AIFeedbackPromptDto> getAIFeedbacks(String userId) throws MapperException {
            return List.of();
        }
    };

    @Test
    public void feedbackTest(){
        List<AIFeedbackPromptDto> dto = mapper.getAIFeedbacks("qwer1234");

        assertThat(dto.get(0).getFeedback().equals("너무 일찍 청산한 감이 있다. 펀더멘탈이 변하지 않았다면 기준선이 오기 전까진 들고 있는게 낫지 않았을까 싶다."));
    }
}