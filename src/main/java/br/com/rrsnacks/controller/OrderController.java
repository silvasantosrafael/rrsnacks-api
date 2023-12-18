package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.OrderDTO;
import br.com.rrsnacks.service.implement.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired()
    OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        Optional<OrderDTO> OrderDTO = orderService.getById(id);
        return OrderDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderDTO>> getOrder(@RequestParam String login) {
        List<OrderDTO> orderDTOList = orderService.getByCustomerLogin(login);
        return ResponseEntity.ok().body(orderDTOList);
    }

    @PostMapping("save")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO OrderDTO) {
        return ResponseEntity.created(URI.create("")).body(orderService.saveOrMerge(OrderDTO));
    }
}
