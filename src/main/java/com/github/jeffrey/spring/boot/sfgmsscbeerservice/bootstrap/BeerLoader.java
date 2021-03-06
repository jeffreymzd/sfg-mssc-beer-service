package com.github.jeffrey.spring.boot.sfgmsscbeerservice.bootstrap;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

        if (beerRepository.count() == 0) {

            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(337010000001L)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(337010000002L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }

        log.info("Loaded Beers: {}", beerRepository.count());
    }
}
