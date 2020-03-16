package com.github.jeffrey.spring.boot.sfgmsscbeerservice.repository;

import com.github.jeffrey.spring.boot.sfgmsscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by jeffreymzd on 3/16/20
 */
@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
