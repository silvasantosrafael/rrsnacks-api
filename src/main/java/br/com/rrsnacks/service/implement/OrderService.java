package br.com.rrsnacks.service.implement;

import br.com.rrsnacks.dto.CustomerDTO;
import br.com.rrsnacks.dto.OrderDTO;
import br.com.rrsnacks.dto.SnackDTO;
import br.com.rrsnacks.model.Customer;
import br.com.rrsnacks.model.Order;
import br.com.rrsnacks.repository.CustomerRepository;
import br.com.rrsnacks.repository.OrderRepository;
import br.com.rrsnacks.repository.SnackRepository;
import br.com.rrsnacks.service.ServiceStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ServiceStrategy<OrderDTO> {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SnackRepository snackRepository;
    private final ModelMapper mapper;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, SnackRepository snackRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.snackRepository = snackRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderDTO> getAll() {
        return Arrays.stream(mapper.map(orderRepository.findAll(), OrderDTO[].class)).toList();
    }

    @Override
    public Optional<OrderDTO> getById(Long id) {
        return orderRepository.findById(id).map(order -> mapper.map(order, OrderDTO.class));
    }

    public List<OrderDTO> getByCustomerLogin(String login) {
        return Arrays.stream(mapper.map(orderRepository.findByCustomerEmail(login), OrderDTO[].class)).toList();
    }

    @Override
    public OrderDTO saveOrMerge(OrderDTO orderDTO) {
        Order orderEntity = mapper.map(orderDTO, Order.class);
        Order order = orderRepository.save(orderEntity);
        OrderDTO map = mapper.map(order, OrderDTO.class);

        return getDataForResponse(map);
    }

    private OrderDTO getDataForResponse(OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomer().getCustomerId()).stream().findFirst().orElse(null);
        CustomerDTO customerDTO = mapper.map(customer, CustomerDTO.class);
        orderDTO.setCustomer(customerDTO);

        List<SnackDTO> listSnackDTO = orderDTO.getSnacks()
                .stream()
                .map(snackDTO -> snackRepository.findById(snackDTO.getSnackId()).stream().findFirst().orElse(null))
                .map(snack -> mapper.map(snack, SnackDTO.class))
                .toList();

        orderDTO.setSnacks(listSnackDTO);

        return orderDTO;
    }

}
