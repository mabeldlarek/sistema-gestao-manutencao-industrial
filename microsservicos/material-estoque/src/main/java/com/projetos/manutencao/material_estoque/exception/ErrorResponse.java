package com.projetos.manutencao.material_estoque.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, String> fieldErrors;
    private String message;
    private String path;

    public ErrorResponse(HttpStatus status, String message, Map<String, String> fieldErrors, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.path = path;
    }

    // getters e setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public Map<String, String> getFieldErrors() { return fieldErrors; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
}
