package com.curbanii.board.application.Config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionMessage {
    String message;
    String details;

    public ExceptionMessage(String message, String details) {
        this.message = message;
        this.details = details;
    }
}