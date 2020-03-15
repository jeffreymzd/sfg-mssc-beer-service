package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.controller;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/15/20
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    private BeerService beerService;

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto) {

        BeerDto savedDto = beerService.save(beerDto);

        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

        return new ResponseEntity(header, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {

        beerService.updateBeer(beerId, beerDto);
    }

    @PutMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {

        beerService.deleteBeer(beerId);
    }

}
