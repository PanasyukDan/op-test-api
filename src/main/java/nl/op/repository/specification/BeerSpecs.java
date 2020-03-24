package nl.op.repository.specification;

import nl.op.domain.Beer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class BeerSpecs {
    public static Specification<Beer> fullTextSearch(String filter) {
        return (Specification<Beer>) (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(builder.like(builder.lower(root.get("name")), "%" + filter.toLowerCase() + "%"));
            predicates.add(builder.like(builder.lower(root.get("description")), "%" + filter.toLowerCase() + "%"));
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
