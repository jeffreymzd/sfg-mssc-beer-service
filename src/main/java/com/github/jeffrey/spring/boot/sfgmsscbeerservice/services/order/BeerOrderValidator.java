package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.order;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository.BeerRepository;
import guru.sfg.brewery.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jeffreymzd on 4/1/20
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public boolean validateOrder(BeerOrderDto beerOrderDto) {
        AtomicInteger beerNotFound = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
            if (null == beerRepository.findByUpc(beerOrderLineDto.getUpc()))
                beerNotFound.incrementAndGet();
        });

        return beerNotFound.get() == 0;
    }
}
