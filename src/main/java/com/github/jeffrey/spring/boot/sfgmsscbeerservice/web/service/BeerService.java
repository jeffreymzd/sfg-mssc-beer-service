package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerPagedList;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/15/20
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto save(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest request);
}
