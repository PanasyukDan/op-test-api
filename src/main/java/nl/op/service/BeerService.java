package nl.op.service;

import nl.op.domain.Beer;
import nl.op.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerService {
    Page<Beer> fullTextSearch(String filter, Pageable pageable);

    User markFavoriteBeer(Long id, UUID userId);
}
