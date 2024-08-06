package com.myinvestment.openai.dto;

import lombok.Getter;

@Getter
public class PortfolioDto {

    String stock;
    String date;
    int amount;
    int quantity;
    int previousQuote;
    int currentQuote;

}
