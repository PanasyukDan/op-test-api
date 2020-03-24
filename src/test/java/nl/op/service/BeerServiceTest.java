package nl.op.service;
import nl.op.domain.Beer;
import nl.op.domain.User;
import nl.op.repository.BeerRepository;
import nl.op.repository.UserRepository;
import nl.op.service.impl.BeerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {
    @InjectMocks
    private BeerServiceImpl beerService;
    @Mock
    private BeerRepository beerRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        Beer beer = new Beer();
        beer.setId(1L);
        UUID userId = UUID.fromString("d697458a-2d58-4609-ba0d-c5c3135fcfff");
        User user = new User(userId);
        User userWithBeer = new User(userId);
        userWithBeer.getBeers().add(beer);
        Mockito.when(beerRepository.findById(beer.getId()))
                .thenReturn(Optional.of(beer));
        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user), Optional.of(userWithBeer));
        Mockito.when(userRepository.save(user))
                .thenReturn(user);
        Mockito.when(userRepository.save(userWithBeer))
                .thenReturn(userWithBeer);
    }


    @Test
    void markFavoriteBeer() {
        UUID userId = UUID.fromString("d697458a-2d58-4609-ba0d-c5c3135fcfff");
        Long beerId = 1L;
        User found = beerService.markFavoriteBeer(beerId, userId);
        Assertions.assertThat(found.getBeers().get(0).getId())
                .isEqualTo(beerId);

        User found2 = beerService.markFavoriteBeer(beerId, userId);
        Assertions.assertThat(found2.getBeers().size())
                .isEqualTo(0);
    }
}

