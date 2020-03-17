package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.exception.NotFoundException;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository.BeerRepository;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.mapper.BeerMapper;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/15/20
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    private Beer findBeerById(UUID beerId) {
        return beerRepository.
                findById(beerId)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {

        return beerMapper.beerToBeerDto(findBeerById(beerId));
    }

    @Override
    public BeerDto save(BeerDto beerDto) {

        return beerMapper
                .beerToBeerDto(beerRepository
                .save(beerMapper
                        .beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {

        Beer beer = findBeerById(beerId);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void deleteBeer(UUID beerId) {

        beerRepository.delete(findBeerById(beerId));
    }
}
