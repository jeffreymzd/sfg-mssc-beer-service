package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.inventory;

import guru.sfg.brewery.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by jeffreymzd on 3/18/20
 */
@Slf4j
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    private final String INVENTORY_PATH_BY_ID = "/api/v1/beer/{beerId}/inventory";
    private final String INVENTORY_PATH_BY_UPC = "/api/v1/beerUpc/{beerUpc}/inventory";
    private final RestTemplate restTemplate;

    @Value("${sfg.brewery.beerInventoryServiceHost}")
    private String beerInventoryServiceHost;

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
//                .errorHandler(new InventoryResponseErrorHandler())
                .build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {

        log.info("Calling Beer Inventory Service by Beer Id");

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
                .exchange(beerInventoryServiceHost + INVENTORY_PATH_BY_ID, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<BeerInventoryDto>>() {}, beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }

    @Override
    public Integer getOnHandInventory(String beerUpc) {

        log.info("Calling Beer Inventory Service by Beer UPC");

        ResponseEntity<List<BeerInventoryDto>> responseEntity;
        try {
            responseEntity= restTemplate
                    .exchange(beerInventoryServiceHost + INVENTORY_PATH_BY_UPC, HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<BeerInventoryDto>>() {
                            }, beerUpc);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return 0;
            } else {
                throw e;
            }
        }

        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }
}
