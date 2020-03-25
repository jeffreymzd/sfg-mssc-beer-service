package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model;

import guru.sfg.beer.common.model.BeerDto;
import guru.sfg.beer.common.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by jeffreymzd on 3/17/20
 */
public class BaseTest {

    BeerDto getDto() {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal(12.95))
                .upc("0631234200036")
                .build();
    }
}
