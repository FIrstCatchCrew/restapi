package com.firstcatchcrew.restapi.species;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, Long> {
    List<Species> findAll();

    Species getSpeciesBySpeciesName(String speciesName);
}
