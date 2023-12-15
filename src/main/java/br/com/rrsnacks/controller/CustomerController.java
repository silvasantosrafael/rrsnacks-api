package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
        CustomerDTO customerDTO = customerService.getById(id);
        return customerDTO != null ? ResponseEntity.ok().body(customerDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("create")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.created(URI.create("")).body(customerService.create(customerDTO));
    }
}
