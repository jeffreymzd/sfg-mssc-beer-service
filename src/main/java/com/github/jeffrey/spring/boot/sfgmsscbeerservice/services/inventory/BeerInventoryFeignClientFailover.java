package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory;

import guru.sfg.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by jeffreymzd on 4/5/20
 */
@RequiredArgsConstructor
@Component
public class BeerInventoryFeignClientFailover implements BeerInventoryServiceFeignClient {

    private final BeerInventoryFailoverFeignClient beerInventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return beerInventoryFailoverFeignClient.getOnHandInventory();
    }

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(String beerUpc) {
        return beerInventoryFailoverFeignClient.getOnHandInventory();
    }
}
