package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.model.Customer;
import br.com.rrsnacks.repository.CustomerRepository;
import br.com.rrsnacks.util.EntityDtoConversionService;
import br.com.rrsnacks.service.ServiceStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService implements ServiceStrategy<CustomerDTO> {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EntityDtoConversionService service;

    @Override
    public List<CustomerDTO> getAll() {

        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(customer ->  modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> modelMapper.map(customer, CustomerDTO.class));
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customerEntity = service.convertToEntity(customerDTO);
        Customer customer = customerRepository.save(customerEntity);
        return modelMapper.map(customer, CustomerDTO.class);
    }

}
