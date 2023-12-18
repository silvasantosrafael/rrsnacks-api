package br.com.rrsnacks.repository;

import br.com.rrsnacks.model.Snack;
import org.springframework.data.repository.CrudRepository;

public interface SnackRepository extends CrudRepository<Snack, Long> {
}
