package dev.lam.gira.common.exception;

import dev.lam.gira.common.model.ResponseDTO;
import dev.lam.gira.common.util.ExceptionUtils;
import dev.lam.gira.common.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException) {
        return ResponseUtils.getError(ExceptionUtils.getError(constraintViolationException), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseUtils.getError(ExceptionUtils.getError(methodArgumentNotValidException), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleValidationException(
            ValidationException validationException) {
        return ResponseUtils.getError(ExceptionUtils.getError(validationException), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<ResponseDTO> handleRuntimeException(
//            RuntimeException exception) {
//        return ResponseUtils.getError(ExceptionUtils.getError(exception), HttpStatus.BAD_REQUEST);
//    }

}
