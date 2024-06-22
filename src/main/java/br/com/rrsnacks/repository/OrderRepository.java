package br.com.rrsnacks.repository;

import br.com.rrsnacks.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findByCustomerEmail(String email);
}
