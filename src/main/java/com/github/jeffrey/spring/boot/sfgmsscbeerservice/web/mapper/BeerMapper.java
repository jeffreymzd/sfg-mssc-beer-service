package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.mapper;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import guru.sfg.beer.common.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * Created by jeffreymzd on 3/16/20
 */
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapDecorator.class)
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);
}
