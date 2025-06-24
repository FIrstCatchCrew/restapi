package com.firstcatchcrew.restapi.fishCatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CatchRepository extends JpaRepository<Catch, Long> {

    List<Catch> findAll();

//    List<Catch> findAllCatches();

    List<Catch> findByAvailableTrue(); // uses Spring Data’s keyword parsing to find all with available = true.

    List<Catch> findByFisher_Id(Long id);

    List<Catch> findByFisher_IdAndOrderItemsIsNotEmpty(Long fisherId);

    List<Catch> findByFisher_IdAndAvailableTrue(Long fisherId);

    List<Catch> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Catch> findBySpecies_SpeciesId(Long speciesId);

    List<Catch> findByPickupInfo_Address(String address);

    List<Catch> findBySpecies_SpeciesIdAndPickupInfo_Address(Long speciesId, String pickupAddress);

    List<Catch> findByPriceBetweenAndSpecies_SpeciesNameIgnoreCaseAndPickupInfo_AddressIgnoreCase(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String speciesName,
            String address);

}