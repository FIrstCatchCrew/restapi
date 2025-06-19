package com.firstcatchcrew.restapi.fisherProfile;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FisherProfileService {

    private final FisherProfileRepository fisherRepository;

    public FisherProfileService(FisherProfileRepository fisherRepository) {
        this.fisherRepository = fisherRepository;
    }

    public FisherProfile getFisherById(long id) {
        Optional<FisherProfile> fisherOptional = fisherRepository.findById(id);

        return fisherOptional.orElse(null);
    }

    public FisherProfile createFisher(FisherProfile newFisher) {
        return fisherRepository.save(newFisher);
    }

    public FisherProfile updateFisher(long id, FisherProfile updatedFisher) {
        Optional<FisherProfile> fisherToUpdateOptional = fisherRepository.findById(id);

        if (fisherToUpdateOptional.isPresent()) {
            FisherProfile fisherToUpdate = fisherToUpdateOptional.get();

            fisherToUpdate.setPerson(updatedFisher.getPerson());
            fisherToUpdate.setFishingLicenseNumber(updatedFisher.getFishingLicenseNumber());
            fisherToUpdate.setDefaultLanding(updatedFisher.getDefaultLanding());
            fisherToUpdate.setCatches(updatedFisher.getCatches());

            return fisherRepository.save(fisherToUpdate);
        }

        return null;
    }

    public void deleteFisherById(long id) {
        fisherRepository.deleteById(id);
    }

}
