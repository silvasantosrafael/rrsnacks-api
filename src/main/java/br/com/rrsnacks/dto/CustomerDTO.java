package br.com.rrsnacks.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDTO {
    private Long customerId;
    private String name;
    @Email
    private String email;
    private AddressDTO address;
}

