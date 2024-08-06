package com.myinvestment.diary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PortfolioResponseDto {

    String stock;
    int amount;
    int quantity;
    int previousQuote;
    int currentQuote;

    @Setter
    int revenue;

    @Setter
    double ratio;

}
