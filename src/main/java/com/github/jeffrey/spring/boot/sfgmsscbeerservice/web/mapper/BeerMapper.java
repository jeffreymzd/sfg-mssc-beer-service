package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.mapper;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * Created by jeffreymzd on 3/16/20
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);
}
