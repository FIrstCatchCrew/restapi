package com.firstcatchcrew.restapi.landing;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandingService {
    private final LandingRepository landingRepository;
    public LandingService(LandingRepository landingRepository) {
        this.landingRepository = landingRepository;
    }

    public List<Landing> getAllLandings() {
        return landingRepository.findAll();
    }

    public Landing getLandingById(long landingId) {
        return landingRepository.findById(landingId).orElse(null);
    }

    @Transactional
    public Landing createLanding(Landing newLanding) {
        return landingRepository.save(newLanding);
    }

    @Transactional
    public Landing updateLanding(long landingId, Landing newLanding) {
        Optional<Landing> landingToUpdateOptional = landingRepository.findById(landingId);
        if (landingToUpdateOptional.isPresent()) {
            Landing landingToUpdate = landingToUpdateOptional.get();

            landingToUpdate.setName(newLanding.getName());
            landingToUpdate.setAddress(newLanding.getAddress());

            return landingRepository.save(newLanding);
        }
        return null;
    }

    public boolean deleteLandingById(long landingId) {
        if (!landingRepository.existsById(landingId)) return false;
        landingRepository.deleteById(landingId);
        return true;
    }

}
