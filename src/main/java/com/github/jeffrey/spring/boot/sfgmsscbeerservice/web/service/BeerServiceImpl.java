package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/15/20
 */
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public BeerDto save(BeerDto beerDto) {
        return BeerDto.builder().id(UUID.randomUUID()).build();
    }
}
