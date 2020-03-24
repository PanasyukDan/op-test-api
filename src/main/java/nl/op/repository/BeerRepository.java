package nl.op.repository;

import nl.op.domain.Beer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {
}
