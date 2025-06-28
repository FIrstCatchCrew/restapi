package com.firstcatchcrew.restapi.fishCatch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CatchRepository extends CrudRepository<Catch, Long> {

    List<Catch> findAll();

    List<Catch> findByAvailableTrue();

    List<Catch> findByFisher_IdAndAvailableTrue(Long fisherId);

    List<Catch> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Catch> findBySpecies_Id(Long speciesId);

    List<Catch> findByLanding_Id(Long landingId);

    List<Catch> findBySpecies_IdAndLanding_Id(Long speciesId, Long landingId);

    List<Catch> findByPriceBetweenAndSpecies_NameIgnoreCaseAndLanding_NameIgnoreCase(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String speciesName,
            String landingName);
}