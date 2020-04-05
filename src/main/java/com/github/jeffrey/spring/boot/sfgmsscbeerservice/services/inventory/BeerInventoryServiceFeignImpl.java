package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory;

import guru.sfg.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by jeffreymzd on 4/5/20
 */
@Profile("local-discovery")
@Slf4j
@RequiredArgsConstructor
@Service
public class BeerInventoryServiceFeignImpl implements BeerInventoryService {

    private final BeerInventoryServiceFeignClient beerInventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("[FeignClient] Calling inventory service, beer Id: {}", beerId);
        ResponseEntity<List<BeerInventoryDto>> responseEntity = beerInventoryServiceFeignClient.getOnHandInventory(beerId);

        return Optional.ofNullable(responseEntity.getBody())
                .get()
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }

    @Override
    public Integer getOnHandInventory(String beerUpc) {
        log.debug("[FeignClient] Calling inventory service, beer UPC: {}", beerUpc);
        ResponseEntity<List<BeerInventoryDto>> responseEntity = beerInventoryServiceFeignClient.getOnHandInventory(beerUpc);

        return Optional.ofNullable(responseEntity.getBody())
                .get()
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }
}
