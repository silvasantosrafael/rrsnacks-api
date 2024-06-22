package br.com.rrsnacks.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long addressId;
    private String street;
    private String number;
    private String district;
    private String complement;
    private String city;
}
