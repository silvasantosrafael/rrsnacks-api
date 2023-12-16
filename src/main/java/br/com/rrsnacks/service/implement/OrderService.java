package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.OrderDTO;
import br.com.rrsnacks.repository.OrderRepository;
import br.com.rrsnacks.service.ServiceStrategy;
import br.com.rrsnacks.util.EntityDtoConversionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService implements ServiceStrategy<OrderDTO> {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EntityDtoConversionService service;

    @Override
    public List<OrderDTO> getAll() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getById(Long id) {
        return orderRepository.findById(id)
                .map(order -> modelMapper.map(order, OrderDTO.class));
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        return null;
    }
}
