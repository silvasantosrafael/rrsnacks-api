package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.OrderDTO;
import br.com.rrsnacks.model.Order;
import br.com.rrsnacks.repository.OrderRepository;
import br.com.rrsnacks.service.ServiceStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ServiceStrategy<OrderDTO> {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper mapper;


    @Override
    public List<OrderDTO> getAll() {
        return Arrays.stream(mapper.map(orderRepository.findAll(), OrderDTO[].class)).toList();
    }

    @Override
    public Optional<OrderDTO> getById(Long id) {
        return orderRepository.findById(id).map(order -> mapper.map(order, OrderDTO.class));
    }

    @Override
    public OrderDTO saveOrMerge(OrderDTO orderDTO) {
        Order orderEntity = mapper.map(orderDTO, Order.class);
        Order order = orderRepository.save(orderEntity);

        return mapper.map(order, OrderDTO.class);
    }

}
