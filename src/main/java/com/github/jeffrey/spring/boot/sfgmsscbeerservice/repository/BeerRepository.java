package com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/16/20
 */
@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, PageRequest request);

    Page<Beer> findAllByBeerName(String beerName, PageRequest request);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, PageRequest request);
}
