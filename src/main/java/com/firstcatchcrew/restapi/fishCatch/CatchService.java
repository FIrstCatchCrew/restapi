package com.firstcatchcrew.restapi.fishCatch;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatchService {
    private final CatchRepository catchRepository;
    public CatchService(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    public List<Catch> getAllCatches() {
        return catchRepository.findAll();
    }

    public List<Catch> getAllAvailableCatches(){
        return catchRepository.findByAvailableTrue();

    }

    public List<Catch> getCatchesByFisherId(long fisherId) {
        return catchRepository.findByFisher_Id(fisherId);
    }

    public List<Catch> getSoldCatchesByFisherId(long fisherId) {
        return catchRepository.findByFisherIdAndOrderItemIsNotNull(fisherId);
    }


//    public Catch getCatchById(long id) {
//        return catchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Catch not found"));
//    }

    public Catch createCatch(Catch newCatch) {
        //newCatch.setCatchDate(LocalDateTime.now());

        // Handle default pickup info logic in the model
        //newCatch.initializeDefaultPickupIfMissing();

        newCatch.updateAvailabilityStatus();

        return catchRepository.save(newCatch);
    }

//    public Catch createCatch(Species species, FisherProfile fisher, BigDecimal quantityInKg, BigDecimal price, GeoLocation geoLocation, PickupInfo pickupInfo) {
//        Catch newCatch = new Catch(species, fisher, quantityInKg, price, geoLocation);
//
//        if (pickupInfo == null) {
//            PickupInfo defaultPickup = new PickupInfo("TBD", "TBD", newCatch.getCatchDate().withHour(12).withMinute(0));
//            newCatch.setPickupInfo(defaultPickup);
//        } else if (!pickupInfo.getPickupTime().toLocalDate().isEqual(newCatch.getCatchDate().toLocalDate())) {
//            throw new IllegalArgumentException("Pickup must be on the same day as the catch.");
//        } else {
//            newCatch.setPickupInfo(pickupInfo);
//        }
//
//        newCatch.updateAvailabilityStatus();
//        return catchRepository.save(newCatch);
//    }


    public Catch updateCatch(long id, Catch updatedCatch) {
        Optional<Catch> catchToUpdateOptional = catchRepository.findById(id);

        if (catchToUpdateOptional.isPresent()) {
            Catch catchToUpdate = catchToUpdateOptional.get();

            catchToUpdate.setFisher(updatedCatch.getFisher());
            catchToUpdate.setSpecies(updatedCatch.getSpecies());
            catchToUpdate.setPrice(updatedCatch.getPrice());
            catchToUpdate.setQuantityInKg(updatedCatch.getQuantityInKg());
            catchToUpdate.setGeoLocation(updatedCatch.getGeoLocation());

            // Delegate pickup logic to the model
            //catchToUpdate.applyOrDefaultPickupInfo(updatedCatch.getPickupInfo());

            catchToUpdate.updateAvailabilityStatus();

            return catchRepository.save(catchToUpdate);
        }
        return null;
    }

    public void updateAvailabilityForAllCatches() {
        List<Catch> allCatches = catchRepository.findAll();

        for (Catch c : allCatches) {
            c.updateAvailabilityStatus(); // already checks logic
        }

        catchRepository.saveAll(allCatches);
    }


    public void deleteCatchById(long id) {
        catchRepository.deleteById(id);
    }
}
