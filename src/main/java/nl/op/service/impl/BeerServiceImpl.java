package nl.op.service.impl;

import nl.op.domain.Beer;
import nl.op.domain.User;
import nl.op.repository.BeerRepository;
import nl.op.repository.UserRepository;
import nl.op.repository.specification.BeerSpecs;
import nl.op.service.BeerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Component
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final UserRepository userRepository;

    public BeerServiceImpl(BeerRepository beerRepository, UserRepository userRepository) {
        this.beerRepository = beerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<Beer> fullTextSearch(String filter, Pageable pageable) {
        return beerRepository.findAll(BeerSpecs.fullTextSearch(filter), pageable);
    }

    @Override
    public User markFavoriteBeer(Long id, UUID userId) {
        Beer beer = beerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if (user.getBeers().contains(beer)) {
            user.getBeers().remove(beer);
        } else {
            user.getBeers().add(beer);
        }
        return userRepository.save(user);
    }
}
