package br.com.rrsnacks.controller;

import br.com.rrsnacks.dto.OrderDTO;
import br.com.rrsnacks.service.implement.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        Optional<OrderDTO> orderDTO = orderService.getById(id);
        return orderDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderDTO>> getOrderByCustomerLogin(@RequestParam String login) {
        List<OrderDTO> orderDTOList = orderService.getByCustomerLogin(login);
        return ResponseEntity.ok().body(orderDTOList);
    }

    @PostMapping("save")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        if (orderDTO.getOrderId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrMerge(orderDTO));
        }

        return ResponseEntity.ok().body(orderService.saveOrMerge(orderDTO));
    }
}
