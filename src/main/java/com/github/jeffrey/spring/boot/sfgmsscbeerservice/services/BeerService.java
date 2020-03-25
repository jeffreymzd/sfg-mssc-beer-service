package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services;

import guru.sfg.beer.common.model.BeerDto;
import guru.sfg.beer.common.model.BeerPagedList;
import guru.sfg.beer.common.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/15/20
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto save(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest request, Boolean showInventoryOnHand);

    BeerDto getBeerByUpc(String upc, Boolean showInventoryOnHand);
}
