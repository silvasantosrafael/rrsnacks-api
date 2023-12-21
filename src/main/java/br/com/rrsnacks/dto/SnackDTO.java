package br.com.rrsnacks.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SnackDTO {
    private Long snackId;
    @NotNull
    private String name;
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;
}

