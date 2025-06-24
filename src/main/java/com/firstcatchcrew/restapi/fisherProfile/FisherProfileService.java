package com.firstcatchcrew.restapi.fisherProfile;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FisherProfileService {

    private final FisherProfileRepository fisherRepository;

    public FisherProfileService(FisherProfileRepository fisherRepository) {
        this.fisherRepository = fisherRepository;
    }

    public List<FisherProfile> getAllFishers() {
        return StreamSupport
                .stream(fisherRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public FisherProfile getFisherById(long id) {
        Optional<FisherProfile> fisherOptional = fisherRepository.findById(id);

        return fisherOptional.orElse(null);
    }

    public FisherProfile createFisher(FisherProfile newFisher) {
        return fisherRepository.save(newFisher);
    }

    public FisherProfile updateFisher(long id, FisherProfile updatedFisher) {
        return fisherRepository.findById(id)
                .map(existing -> {
                    existing.setPerson(updatedFisher.getPerson());
                    existing.setFishingLicenseNumber(updatedFisher.getFishingLicenseNumber());
                    existing.setDefaultLanding(updatedFisher.getDefaultLanding());
                    return fisherRepository.save(existing);
                }).orElse(null);
    }

    public void deleteFisherById(long id) {
        fisherRepository.deleteById(id);
    }

}
