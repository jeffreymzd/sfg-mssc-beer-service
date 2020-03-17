package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

/**
 * Created by jeffreymzd on 3/17/20
 */
@JsonTest
class BeerDtoTest extends BaseTest{

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {

        String jsonString = objectMapper.writeValueAsString(getDto());

        System.out.println(jsonString);
    }

    @Test
    void testDeserializeJsonString() throws JsonProcessingException {

        String jsonString = "{\"id\":\"ea7e4087-fd1f-49cc-afba-ade7f27e55a8\",\"version\":null,\"createdDate\":\"2020-03-17T13:34:59.929729-04:00\",\"lastModifiedDate\":\"2020-03-17T13:34:59.930801-04:00\",\"beerName\":\"Galaxy\",\"beerStyle\":\"PALE_ALE\",\"upc\":123456789012,\"price\":12.949999999999999289457264239899814128875732421875,\"quantityOnHand\":null}";

        BeerDto beerDto = objectMapper.readValue(jsonString, BeerDto.class);

        System.out.println(beerDto);
    }

}