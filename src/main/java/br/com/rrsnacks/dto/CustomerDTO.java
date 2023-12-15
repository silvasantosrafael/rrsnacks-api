package br.com.rrsnacks.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String email;
    private List<AddressDTO> address;
}

