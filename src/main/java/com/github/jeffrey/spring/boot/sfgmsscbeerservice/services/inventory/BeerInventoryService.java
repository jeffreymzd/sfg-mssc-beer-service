package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/18/20
 */
public interface BeerInventoryService {

    Integer getOnHandInventory(UUID beerId);
}
