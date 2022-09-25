package dev.lam.gira.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ExceptionUtils {
    public static final String DEFAULT_UNEXPECTED_MESSAGE = "Ops! Something went wrong...";

    public static List<String> getError(ConstraintViolationException constraintViolationException) {
        return constraintViolationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    public static List<String> getError(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

    public static List<String> getError(RuntimeException exception) {
        return List.of(DEFAULT_UNEXPECTED_MESSAGE);
    }
}
