package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.brewing;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository.BeerRepository;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory.BeerInventoryService;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.mapper.BeerMapper;
import guru.sfg.brewery.model.event.BrewBeerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.jeffrey.spring.boot.sfgmsscbeerservice.config.JmsConfiguration.BREWING_REQUEST_QUEUE;

/**
 * Created by jeffreymzd on 3/23/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers =  beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getUpc());
            log.info("Inventory minOnHand={}, current={}", beer.getMinOnHand(), invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                log.info("Send new brewing request UPC={} ShortInHand={}", beer.getUpc(), beer.getMinOnHand() - invQOH);
                jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
