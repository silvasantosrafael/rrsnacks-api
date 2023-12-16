package br.com.rrsnacks.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SnackDTO {
    private Long snackId;
    private String name;
    private String description;
    private BigDecimal price;
}

