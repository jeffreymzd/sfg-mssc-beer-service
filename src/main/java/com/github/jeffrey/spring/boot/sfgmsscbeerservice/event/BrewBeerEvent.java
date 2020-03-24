package com.github.jeffrey.spring.boot.sfgmsscbeerservice.event;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;

/**
 * Created by jeffreymzd on 3/23/20
 */
public class BrewBeerEvent extends BeerEvent {

    private static final long serialVersionUID = -4456108621665223455L;

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
