package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.service.implement.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping("create")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.created(URI.create("")).body(customerService.create(customerDTO));
    }
}
