package com.github.jeffrey.spring.boot.sfgmsscbeerservice.event;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by jeffreymzd on 3/23/20
 */
@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent implements Serializable {


    private static final long serialVersionUID = 1935424428211105886L;
    private final BeerDto beerDto;
}
