package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/19/20
 */
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {

        UUID id = UUID.randomUUID();
        Integer quantity = beerInventoryService.getOnHandInventory(id);
        System.out.println("Id: " + id);
        System.out.println("Returned quantityOnHand: " + quantity);
    }
}