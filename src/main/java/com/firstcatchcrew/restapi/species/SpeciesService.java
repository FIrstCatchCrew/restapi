package com.firstcatchcrew.restapi.species;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {
    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public Species getSpeciesById(long id) {
        return speciesRepository.findById(id).orElse(null);
    }

    public List<Species> getAllSpecies() {
        return speciesRepository.findAll();
    }

    @Transactional
    public Species createSpecies(Species newSpecies) {
        return speciesRepository.save(newSpecies);
    }

    @Transactional
    public Species updateSpecies(long id, Species updatedSpecies) {

        Optional<Species> speciesToUpdateOptional = speciesRepository.findById(id);

        if (speciesToUpdateOptional.isPresent()) {
            Species speciesToUpdate = speciesToUpdateOptional.get();

            speciesToUpdate.setName(updatedSpecies.getName());
            speciesToUpdate.setDescription(updatedSpecies.getDescription());
            speciesToUpdate.setImageUrl(updatedSpecies.getImageUrl());

            return speciesRepository.save(speciesToUpdate);
        }

        return null;
    }

    public boolean deleteSpeciesById(long id) {
        if (!speciesRepository.existsById(id)) return false;
        speciesRepository.deleteById(id);
        return true;
    }

}
