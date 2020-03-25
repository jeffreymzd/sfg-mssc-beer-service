package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.brewing;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.config.JmsConfiguration;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository.BeerRepository;
import guru.sfg.beer.common.event.BrewBeerEvent;
import guru.sfg.beer.common.event.NewInventoryEvent;
import guru.sfg.beer.common.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jeffreymzd on 3/23/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingRequestListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfiguration.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent brewBeerEvent) {

        BeerDto beerDto = brewBeerEvent.getBeerDto();
        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.info("Brewed beer {} MinOnHand={}, QOH={}", beer.getId(), beer.getMinOnHand(), beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfiguration.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
