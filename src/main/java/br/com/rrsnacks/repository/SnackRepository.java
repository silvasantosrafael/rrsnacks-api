package br.com.rrsnacks.repository;

import br.com.rrsnacks.model.Snack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SnackRepository extends CrudRepository<Snack, Long> {
    @Query(value = "SELECT * FROM snacks ORDER BY snack_id", nativeQuery = true)
    Iterable<Snack> findAllGroupBySnackId();
}
