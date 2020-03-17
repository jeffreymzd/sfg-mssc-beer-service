package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by jeffreymzd on 3/17/20
 */
@JsonTest
@ActiveProfiles("snake")
public class BeerDtoSnakeTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSnake() throws JsonProcessingException {

        String jsonString = objectMapper.writeValueAsString(getDto());

        System.out.println(jsonString);
    }

    @Test
    void testSnakeDeserialize() throws JsonProcessingException {

        String jsonString = "{\"id\":\"579e61cd-295b-44f3-a2d4-5225eb63b077\",\"version\":null,\"created_date\":\"20200317T15:54:45-0400\",\"last_modified_date\":\"20200317T15:54:45-0400\",\"beer_name\":\"Galaxy\",\"beer_style\":\"PALE_ALE\",\"upc\":123456789012,\"price\":12.949999999999999289457264239899814128875732421875,\"quantity_on_hand\":null}";

        BeerDto dto = objectMapper.readValue(jsonString, BeerDto.class);

        System.out.println(dto);
    }

    @Test
    void testSnakeDesirialize_defaultNamingStrategy() throws JsonProcessingException {

        String jsonString = "{\"id\":\"ea7e4087-fd1f-49cc-afba-ade7f27e55a8\",\"version\":null,\"createdDate\":\"2020-03-17T13:34:59.929729-04:00\",\"lastModifiedDate\":\"2020-03-17T13:34:59.930801-04:00\",\"beerName\":\"Galaxy\",\"beerStyle\":\"PALE_ALE\",\"upc\":123456789012,\"price\":12.949999999999999289457264239899814128875732421875,\"quantityOnHand\":null}";

        BeerDto dto = objectMapper.readValue(jsonString, BeerDto.class);

        System.out.println(dto);
    }

    @Test
    void testSnakeDesirialize_kebabtNamingStrategy() throws JsonProcessingException {

        String jsonString = "{\"id\":\"46894952-cc50-4860-b68b-b3e3716accc1\",\"version\":null,\"created-date\":\"2020-03-17T13:58:40.463939-04:00\",\"last-modified-date\":\"2020-03-17T13:58:40.464936-04:00\",\"beer-name\":\"Galaxy\",\"beer-style\":\"PALE_ALE\",\"upc\":123456789012,\"price\":12.949999999999999289457264239899814128875732421875,\"quantity-on-hand\":null}";

        BeerDto dto = objectMapper.readValue(jsonString, BeerDto.class);

        System.out.println(dto);
    }
}
