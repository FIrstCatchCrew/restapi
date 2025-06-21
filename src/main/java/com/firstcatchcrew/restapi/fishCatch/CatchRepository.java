package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CatchRepository extends JpaRepository<CatchViewDTO, Long> {
    List<CatchViewDTO> findAll();

    List<CatchViewDTO> findByAvailableTrue(); // uses Spring Data’s keyword parsing to find all with available = true.

    List<CatchViewDTO> findByFisher_Id(Long id);

    List<CatchViewDTO> findByFisher_IdAndOrderItemIsNotNull(Long fisherId);

    List<CatchViewDTO> findByFisher_IdAndAvailableTrue(Long fisherId);

    List<CatchViewDTO> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<CatchViewDTO> findBySpecies_Id(Long speciesId);

    List<CatchViewDTO> findByPickupInfo_Address(String address);

    List<CatchViewDTO> findBySpecies_IdAndLocation(Long speciesId, String pickupAddress);
}