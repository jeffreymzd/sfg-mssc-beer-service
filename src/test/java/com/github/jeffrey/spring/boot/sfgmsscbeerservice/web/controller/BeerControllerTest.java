package com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerStyleEnum;
import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.service.BeerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // original import without REST Docs

/**
 * Created by jeffreymzd on 3/15/20
 */
@AutoConfigureRestDocs
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;

    @Autowired
    MockMvc mockMvc;

    private BeerDto getValidBeerDto() {

        return BeerDto.builder()
                .beerName("Stella")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(2.5))
                .upc(1234L)
                .quantityOnHand(100)
                .build();
    }

    @Test
    void getBeer() throws Exception {

        given(beerService.getBeerById(any())).willReturn(getValidBeerDto());

        validBeer = getValidBeerDto();

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.beerName", is(validBeer.getBeerName())))
                .andDo(document("v1/beer",
                        // documenting path parameters
                        pathParameters(
                                parameterWithName("beerId").description("UUID of desired beer to get.")
                        ),
                        // documenting response fields
                        responseFields(
                               fieldWithPath("id").description("Id of Beer"),
                               fieldWithPath("version").description("Version Number"),
                               fieldWithPath("createdDate").description("Date Created"),
                               fieldWithPath("lastModifiedDate").description("Date Updated"),
                               fieldWithPath("beerName").description("Beer Name"),
                               fieldWithPath("beerStyle").description("Beer Style"),
                               fieldWithPath("upc").description("UPC of Beer"),
                               fieldWithPath("price").description("Price"),
                               fieldWithPath("quantityOnHand").description("Quantity On Hand")
                        )));
    }

    @Test
    void saveNewBeer() throws Exception {

        given(beerService.save(any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer",
                        requestFields(
                               fieldWithPath("id").ignored(),
                               fieldWithPath("version").ignored(),
                               fieldWithPath("createdDate").ignored(),
                               fieldWithPath("lastModifiedDate").ignored(),
                               fieldWithPath("beerName").description("Beer Name"),
                               fieldWithPath("beerStyle").description("Beer Style"),
                               fieldWithPath("upc").description("UPC of Beer"),
                               fieldWithPath("price").description("Price"),
                               fieldWithPath("quantityOnHand").ignored()
                        )));
    }

    @Test
    void updateBeer() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {

        mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}