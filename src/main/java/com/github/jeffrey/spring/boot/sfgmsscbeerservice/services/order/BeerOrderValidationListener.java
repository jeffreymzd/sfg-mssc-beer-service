package com.github.jeffrey.spring.boot.sfgmsscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.event.ValidateOrderRequest;
import guru.sfg.brewery.model.event.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.github.jeffrey.spring.boot.sfgmsscbeerservice.config.JmsConfiguration.VALIDATE_ORDER_QUEUE;
import static com.github.jeffrey.spring.boot.sfgmsscbeerservice.config.JmsConfiguration.VALIDATE_ORDER_RESPONSE_QUEUE;

/**
 * Created by jeffreymzd on 4/1/20
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;

    @JmsListener(destination = VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        BeerOrderDto beerOrderDto = validateOrderRequest.getBeerOrderDto();

        log.debug("Validate order Id: {}", beerOrderDto.getId());

        Boolean isValid = beerOrderValidator.validateOrder(beerOrderDto);
        jmsTemplate.convertAndSend(VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
                .beerOrderId(beerOrderDto.getId())
                .isValid(isValid)
                .build());
    }
}
