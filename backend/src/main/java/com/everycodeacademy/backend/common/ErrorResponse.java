package com.everycodeacademy.backend.common;

import java.time.OffsetDateTime;

public record ErrorResponse(String message, int status, OffsetDateTime timestamp) {
}
