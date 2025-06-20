package com.firstcatchcrew.restapi.landing;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LandingService {
    private final LandingRepository landingRepository;
    public LandingService(LandingRepository landingRepository) {
        this.landingRepository = landingRepository;
    }

    public Landing getLandingById(long id) {
        return landingRepository.findById(id).orElse(null);
    }

    public Landing createLanding(Landing newLanding) {
        return landingRepository.save(newLanding);
    }

    public Landing updateLanding(long id, Landing newLanding) {
        Optional<Landing> landingToUpdateOptional = landingRepository.findById(id);
        if (landingToUpdateOptional.isPresent()) {
            Landing landingToUpdate = landingToUpdateOptional.get();

            landingToUpdate.setName(newLanding.getName());
            landingToUpdate.setAddress(newLanding.getAddress());

            return landingRepository.save(newLanding);
        }
        return null;

    }

    public void deleteLandingById(long id) {
        landingRepository.deleteById(id);
    }

}
