package br.com.rrsnacks.util;

import br.com.rrsnacks.dto.AddressDTO;
import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.model.Address;
import br.com.rrsnacks.model.Customer;
import br.com.rrsnacks.model.Snack;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityDtoConversionService {

    public Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        List<Address> addresses = Optional
                .ofNullable(customerDTO.getAddress())
                .orElse(List.of())
                .stream()
                .map(addressDTO -> this.convertToEntity(addressDTO, customer))
                .toList();

        customer.setAddress(addresses);

        return customer;
    }

    public Address convertToEntity(AddressDTO addressDTO, Customer customer) {
        Address address = new Address();
        address.setCustomer(customer);
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setDistrict(addressDTO.getDistrict());
        address.setComplement(addressDTO.getComplement());
        address.setCity(addressDTO.getCity());

        return address;
    }

    public Snack convertToEntity(SnackDTO snackDTO) {
        Snack snack = new Snack();
        snack.setName(snackDTO.getName());
        snack.setDescription(snackDTO.getDescription());
        snack.setPrice(snackDTO.getPrice());

        return snack;
    }
}

