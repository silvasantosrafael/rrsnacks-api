package br.com.rrsnacks.repository;

import br.com.rrsnacks.model.Snack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface SnackRepository extends CrudRepository<Snack, Long> {
}
