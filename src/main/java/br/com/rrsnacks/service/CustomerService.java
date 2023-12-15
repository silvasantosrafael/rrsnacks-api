package br.com.rrsnacks.service;

import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.model.Customer;
import br.com.rrsnacks.repository.CustomerRepository;
import br.com.rrsnacks.service.EntityDtoConversionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EntityDtoConversionService service;

    public List<CustomerDTO> getAll() {
        Iterable<Customer> customers = customerRepository.findAll();
        return Arrays.asList(modelMapper.map(customers, CustomerDTO[].class));
    }

    public CustomerDTO getById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .orElse(null);
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customerEntity = service.convertToEntity(customerDTO);//modelMapper.map(customerDTO, Customer.class);
        Customer customer = customerRepository.save(customerEntity);
        return modelMapper.map(customer, CustomerDTO.class);
    }

}
