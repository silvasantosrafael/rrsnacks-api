package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.model.Customer;
import br.com.rrsnacks.repository.CustomerRepository;
import br.com.rrsnacks.service.ServiceStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ServiceStrategy<CustomerDTO> {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public CustomerService(CustomerRepository customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerDTO> getAll() {
        return Arrays.stream(mapper.map(customerRepository.findAll(), CustomerDTO[].class)).toList();
    }

    @Override
    public Optional<CustomerDTO> getById(Long id) {
        return customerRepository.findById(id).map(customer -> mapper.map(customer, CustomerDTO.class));
    }

    public Optional<CustomerDTO> getByLogin(String login) {
        return customerRepository.findByEmail(login).map(customer -> mapper.map(customer, CustomerDTO.class));
    }

    @Override
    public CustomerDTO saveOrMerge(CustomerDTO customerDTO) {
        Customer customerEntity = mapper.map(customerDTO, Customer.class);
        customerEntity.getAddress().setCustomer(customerEntity);
        Customer customer = customerRepository.save(customerEntity);

        return mapper.map(customer, CustomerDTO.class);
    }

}
