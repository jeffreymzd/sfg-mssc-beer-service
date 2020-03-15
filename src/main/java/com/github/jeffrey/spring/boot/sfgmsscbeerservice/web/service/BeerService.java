package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/15/20
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto save(BeerDto beerDto);
}
