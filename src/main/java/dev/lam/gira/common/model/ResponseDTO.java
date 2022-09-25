package dev.lam.gira.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseDTO {
    private Object content;
    private boolean hasError;
    private List<String> errors;
    private String timestamp;
    private int status;
}
