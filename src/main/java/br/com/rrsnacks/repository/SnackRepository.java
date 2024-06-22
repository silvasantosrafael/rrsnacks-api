package br.com.rrsnacks.repository;

import br.com.rrsnacks.model.Snack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long> {
    @Query(value = "SELECT * FROM snacks ORDER BY snack_id", nativeQuery = true)
    Iterable<Snack> findAllGroupBySnackId();
}
