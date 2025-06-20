package com.firstcatchcrew.restapi.fishCatch;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatchService {
    private final CatchRepository catchRepository;
    public CatchService(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    public Iterable<Catch> getAllCatches() {
        return catchRepository.findAll();
    }

    public Catch getCatchById(long id) {
        return catchRepository.findById(id).orElse(null);
    }

    public Catch createCatch(Catch newCatch) {
        return catchRepository.save(newCatch);
    }
    public Catch updateCatch(long id, Catch updatedCatch) {
        Optional<Catch> catchToUpdateOptional = catchRepository.findById(id);

        if (catchToUpdateOptional.isPresent()) {
            Catch catchToUpdate = catchToUpdateOptional.get();

            catchToUpdate.setFisher(updatedCatch.getFisher());
            catchToUpdate.setSpecies(updatedCatch.getSpecies());
            catchToUpdate.setCatchDate(updatedCatch.getCatchDate());
            catchToUpdate.setPrice(updatedCatch.getPrice());
            catchToUpdate.setQuantityInKg(updatedCatch.getQuantityInKg());
            catchToUpdate.setGeoLocation(updatedCatch.getGeoLocation());
            catchToUpdate.setPickupInfo(updatedCatch.getPickupInfo());

            return catchRepository.save(updatedCatch);
        }
        return null;
    }

    public void deleteCatchById(long id) {
        catchRepository.deleteById(id);
    }
}
