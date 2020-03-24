package com.github.jeffrey.spring.boot.sfgmsscbeerservice.event;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;

/**
 * Created by jeffreymzd on 3/23/20
 */
public class NewInventoryEvent extends BeerEvent {

    private static final long serialVersionUID = 2754018446273430878L;

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
