package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.service.implement.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> getAllCustomer() {
        return ResponseEntity.ok().body(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        Optional<CustomerDTO> customerDTO = customerService.getById(id);
        return customerDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomerDTO> getCustomerByLogin(@RequestParam String login) {
        Optional<CustomerDTO> customerDTO = customerService.getByLogin(login);
        return customerDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        if (customerDTO.getCustomerId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveOrMerge(customerDTO));
        }

        return ResponseEntity.ok().body(customerService.saveOrMerge(customerDTO));
    }
}
