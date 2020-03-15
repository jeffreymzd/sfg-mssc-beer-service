package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/15/20
 */
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public BeerDto save(BeerDto beerDto) {
        return BeerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        log.debug("Updating a beer...");
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("Deleting a beer...");
    }
}
