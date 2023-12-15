package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.OrderDTO;
import br.com.rrsnacks.service.ServiceStrategy;

import java.util.List;
import java.util.Optional;

public class OrderService implements ServiceStrategy<OrderDTO> {
    @Override
    public List<OrderDTO> getAll() {
        return null;
    }

    @Override
    public Optional<OrderDTO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        return null;
    }
}
