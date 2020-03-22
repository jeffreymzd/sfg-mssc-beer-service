package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/18/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto {

    private UUID beerId;
    private String beerUpc;
    private Integer quantityOnHand;
}
