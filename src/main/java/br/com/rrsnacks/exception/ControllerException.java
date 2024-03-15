package br.com.rrsnacks.exception;

import br.com.rrsnacks.exception.handler.ErrorHandler;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerException {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorHandler> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ErrorHandler> errors = new ArrayList<>();
        fieldErrors.forEach(fieldError -> errors.add(new ErrorHandler(fieldError.getField(), null, fieldError.getDefaultMessage())));

        return errors;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorHandler handleDataIntegrityViolationException(ConstraintViolationException exception) {
        return new ErrorHandler(null, exception.getConstraintName(), exception.getErrorMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(FileNotFoundException.class)
    public ErrorHandler handleFileNotFoundException(FileNotFoundException exception) {
        return new ErrorHandler(null, null, exception.getMessage());
    }
}
