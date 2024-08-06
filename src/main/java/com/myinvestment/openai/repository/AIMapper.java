package com.myinvestment.openai.repository;

import com.myinvestment.openai.dto.AIFeedbackPromptDto;
import com.myinvestment.openai.dto.AIStrategyPromptDto;
import com.myinvestment.exception.MapperException;
import com.myinvestment.openai.dto.PortfolioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AIMapper {

    List<AIFeedbackPromptDto> getAIFeedbacks() throws MapperException;
    List<AIStrategyPromptDto> getAIStrategy() throws MapperException;
    List<PortfolioDto> getAllPortfolio() throws MapperException;

}
