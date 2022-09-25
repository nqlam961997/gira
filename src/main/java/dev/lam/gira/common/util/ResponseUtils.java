package dev.lam.gira.common.util;

import dev.lam.gira.common.model.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class ResponseUtils {

    public static ResponseEntity<ResponseDTO> get(Object result, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO
                .builder()
                .content(result)
                .hasError(false)
                .errors(Collections.emptyList())
                .timestamp(DateTimeUtils.now())
                .status(status.value())
                .build()
                , status);
    }

    public static ResponseEntity<ResponseDTO> getError(List<String> result, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO
                        .builder()
                        .content(null)
                        .hasError(true)
                        .errors(result)
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build()
                , status);
    }
}
