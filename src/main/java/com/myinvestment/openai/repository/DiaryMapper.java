package com.myinvestment.openai.repository;

import com.myinvestment.openai.dto.AIFeedbackPromptDto;
import com.myinvestment.openai.dto.AIStrategyPromptDto;
import com.myinvestment.exception.MapperException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {

    List<AIFeedbackPromptDto> getAIFeedbacks(String userId) throws MapperException;
    List<AIStrategyPromptDto> getAIStrategy(String userId) throws MapperException;

}
