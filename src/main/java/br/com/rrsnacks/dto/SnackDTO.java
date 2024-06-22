package br.com.rrsnacks.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SnackDTO {
    private Long snackId;
    @NotNull
    private String name;
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;
    private MultipartFile imageFile;
    private String image;
}

