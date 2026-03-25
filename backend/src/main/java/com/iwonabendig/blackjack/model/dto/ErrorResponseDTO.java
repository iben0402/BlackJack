package com.iwonabendig.blackjack.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponseDTO {
    private int status;
    private String message;
    private String timestamp;
}