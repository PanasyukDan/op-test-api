package nl.op.service.impl;

import nl.op.domain.Beer;
import nl.op.repository.BeerRepository;
import nl.op.service.DownloadDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class DownloadDataServiceImpl implements DownloadDataService {
    private static final Logger logger = LoggerFactory.getLogger(DownloadDataServiceImpl.class);

    final RestTemplate restTemplate;
    final BeerRepository beerRepository;

    public DownloadDataServiceImpl(RestTemplate restTemplate, BeerRepository beerRepository) {
        this.restTemplate = restTemplate;
        this.beerRepository = beerRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void downloadData() {
        List<Beer> beers;
        int pageSize = 80;
        int pageNumber = 1;
        logger.info("Downloading beers");
        do {
            beers = getBeersByPage(pageSize, pageNumber);
            if (beers != null && !beers.isEmpty()) {
                beerRepository.saveAll(beers);
            }
            pageNumber++;
        } while (beers!=null && !beers.isEmpty());
        logger.info("All beers are downloaded");
    }

    public List<Beer> getBeersByPage(int pageSize, int pageNumber) {
        ResponseEntity<List<Beer>> response;
        try {
            response =
                    restTemplate.exchange("https://api.punkapi.com/v2/beers?per_page=" + pageSize + "&page=" + pageNumber,
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Beer>>() {
                            });
        } catch (final HttpClientErrorException e) {
            logger.error("Downloading beers failed with error {} {}", e.getStatusCode(), e.getResponseBodyAsString());
            return null;
        }
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }

}
