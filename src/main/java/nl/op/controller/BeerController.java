package nl.op.controller;

import nl.op.domain.Beer;
import nl.op.domain.User;
import nl.op.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*") //TODO remove in production
@RequestMapping("/beers")
public class BeerController {

    private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

    final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Beer>> getBeers(@RequestParam(value = "filter", required = false) String filter,
                                               Pageable pageable) {
        logger.info("REST request to get Beers");
        Page<Beer> response = beerService.fullTextSearch(filter, pageable);
        return ResponseEntity.ok(response);
    }

    //request header made for emulating user Authorization
    @PostMapping(value = "/favorite-beer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> markFavoriteBeer(@PathVariable Long id, @RequestHeader("Authorization") UUID userId) {
        logger.info("REST request to mark BeerID={} as favorite for UserID={}", id, userId);
        User response = beerService.markFavoriteBeer(id, userId);
        return ResponseEntity.ok(response);
    }

}
