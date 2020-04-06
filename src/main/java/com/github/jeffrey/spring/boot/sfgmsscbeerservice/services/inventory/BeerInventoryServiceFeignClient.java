package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory;

import guru.sfg.brewery.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * Created by jeffreymzd on 4/5/20
 */
@FeignClient(name = "inventory-service", fallback = BeerInventoryFeignClientFailover.class)
public interface BeerInventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH_BY_ID)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);

    @RequestMapping(method = RequestMethod.GET, value = BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH_BY_UPC)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable String beerUpc);
}
