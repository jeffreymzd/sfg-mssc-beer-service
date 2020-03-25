package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.mapper;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory.BeerInventoryService;
import guru.sfg.beer.common.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jeffreymzd on 3/18/20
 */
@Component
public abstract class BeerMapDecorator implements BeerMapper {

    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {

        return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {

        BeerDto dto = mapper.beerToBeerDto(beer);

//        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(dto.getId()));
        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(dto.getUpc()));

        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}
