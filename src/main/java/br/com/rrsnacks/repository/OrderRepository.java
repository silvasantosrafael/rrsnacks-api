package br.com.rrsnacks.repository;

import br.com.rrsnacks.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
