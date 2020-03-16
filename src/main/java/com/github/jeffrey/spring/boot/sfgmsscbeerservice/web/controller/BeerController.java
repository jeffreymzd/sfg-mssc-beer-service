package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.controller;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public ResponseEntity<BeerDto> getBeer(@NotNull @PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {

        BeerDto savedDto = beerService.save(beerDto);

        // todo impl so savedDto should return valid UUID
        if (null == savedDto.getId()) savedDto.setId(UUID.randomUUID());

        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

        return new ResponseEntity(header, HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeer(@NotNull @PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {

        beerService.updateBeer(beerId, beerDto);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@NotNull @PathVariable("beerId") UUID beerId) {

        beerService.deleteBeer(beerId);
    }

}
