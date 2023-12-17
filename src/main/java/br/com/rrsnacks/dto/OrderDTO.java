package br.com.rrsnacks.dto;

import br.com.rrsnacks.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long orderId;
    private CustomerDTO customer;
    private PaymentType paymentType;
    private List<SnackDTO> snacks = new ArrayList<>();
    private Date orderDate;
}
