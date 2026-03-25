package com.iwonabendig.blackjack.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDTO {
    private int status;
    private String message;
    private String timestamp;
}