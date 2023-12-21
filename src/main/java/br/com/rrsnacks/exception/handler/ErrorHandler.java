package br.com.rrsnacks.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorHandler {
    private String field;
    private String constraint;
    private String message;

}
