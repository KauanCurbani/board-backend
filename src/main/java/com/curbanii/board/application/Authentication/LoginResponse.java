package com.curbanii.board.application.Authentication;

public record LoginResponse (String token, long expiresAT) {
}
