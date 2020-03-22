package com.github.jeffrey.spring.boot.sfgmsscbeerservice.bootstrap;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by jeffreymzd on 3/16/20
 */
@Component
@Slf4j
public class BeerLoader implements CommandLineRunner {

    @Autowired
    private BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        
        loadBeerObjects();
    }

    private void loadBeerObjects() {

        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());

        if (beerRepository.count() == 0) {

            log.info("Data initialization via BeerLoader...");

            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc("0631234200036")
                    .price(new BigDecimal("12.95"))
                    .createdDate(currentTimestamp)
                    .lastModifiedDate(currentTimestamp)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc("0631234300019")
                    .price(new BigDecimal("12.95"))
                    .createdDate(currentTimestamp)
                    .lastModifiedDate(currentTimestamp)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Pinball Porter")
                    .beerStyle("PORTER")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc("0083783375213")
                    .price(new BigDecimal("11.95"))
                    .createdDate(currentTimestamp)
                    .lastModifiedDate(currentTimestamp)
                    .build());

            log.info("Data initialization via BeerLoader: completed");
        }

        log.info("Loaded Beers: {}", beerRepository.count());
    }
}
