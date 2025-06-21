package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.dto.CatchCreateDTO;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import com.firstcatchcrew.restapi.fishCatch.mapper.CatchMapper;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfileRepository;
import com.firstcatchcrew.restapi.species.Species;
import com.firstcatchcrew.restapi.species.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatchService {
    private final CatchRepository catchRepository;
    private final FisherProfileRepository fisherRepository;
    private final SpeciesRepository speciesRepository;


    public CatchService(CatchRepository catchRepository, FisherProfileRepository fisherRepository, SpeciesRepository speciesRepository, CatchMapper catchMapper) {
        this.catchRepository = catchRepository;
        this.fisherRepository = fisherRepository;
        this.speciesRepository = speciesRepository;
    }

    public CatchViewDTO getCatchById(long id) {
        return catchRepository.findById(id)
                .map(CatchMapper::toViewDTO)
                .orElse(null);
    }

    public List<CatchViewDTO> getAllCatches() {
        return catchRepository.findAll()
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getAllAvailableCatches(){
        return catchRepository.findByAvailableTrue()
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();

    }

    public List<CatchViewDTO> getCatchesByFisherId(long fisherId) {
        return catchRepository.findByFisher_Id(fisherId)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getSoldCatchesByFisherId(long fisherId) {
        return catchRepository.findByFisher_IdAndOrderItemIsNotNull(fisherId)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getAvailableCatchesByFisherId(long fisherId) {
        return catchRepository.findByFisher_IdAndAvailableTrue(fisherId)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getCatchesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return catchRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

//    public Catch createCatch(Catch newCatch) {
//        //newCatch.setCatchDate(LocalDateTime.now());
//
//        // Handle default pickup info logic in the model
//        //newCatch.initializeDefaultPickupIfMissing();
//
//        newCatch.updateAvailabilityStatus();
//
//        return catchRepository.save(newCatch);
//    }

    @Transactional
    public Catch createCatch(CatchCreateDTO dto) {
        FisherProfile fisher = fisherRepository.findById(dto.getFisherId())
                .orElseThrow(() -> new IllegalArgumentException("Fisher with id " + dto.getFisherId() + " not found."));
        Species species = speciesRepository.findById(dto.getSpeciesId())
                .orElseThrow(() -> new IllegalArgumentException("Species with id " + dto.getSpeciesId() + " not found."));

        Catch newCatch = CatchMapper.fromCreateDTO(dto, fisher, species);
        newCatch.updateAvailabilityStatus();
        return catchRepository.save(newCatch);
    }


    @Transactional
    public Catch updateCatch(long id, Catch updatedCatch) {
        Optional<Catch> catchToUpdateOptional = catchRepository.findById(id);

        if (catchToUpdateOptional.isPresent()) {
            Catch catchToUpdate = catchToUpdateOptional.get();

            catchToUpdate.setFisher(updatedCatch.getFisher());
            catchToUpdate.setSpecies(updatedCatch.getSpecies());
            catchToUpdate.setPrice(updatedCatch.getPrice());
            catchToUpdate.setQuantityInKg(updatedCatch.getQuantityInKg());
            catchToUpdate.setGeoLocation(updatedCatch.getGeoLocation());

            // CLEANUP: Delegate pickup logic to the model
            // CLEANUP: catchToUpdate.applyOrDefaultPickupInfo(updatedCatch.getPickupInfo());

            catchToUpdate.updateAvailabilityStatus();

            return catchRepository.save(catchToUpdate);
        }
        return null;
    }

    @Transactional
    public void updateAvailabilityForAllCatches() {
        List<Catch> allCatches = catchRepository.findAll();

        for (Catch catches : allCatches) {
            catches.updateAvailabilityStatus(); // already checks logic
        }

        catchRepository.saveAll(allCatches);
    }


    public void deleteCatchById(long id) {
        catchRepository.deleteById(id);
    }

    public List<CatchViewDTO> getCatchesByLocation(String pickupAddress) {
        return catchRepository.findByPickupInfo_Address(pickupAddress);
    }


    public List<CatchViewDTO> getCatchesBySpeciesName(String speciesName) {
        return catchRepository.findBySpecies_Id();
    }

    public List<CatchViewDTO> getCatchesBySpeciesNameAndLocation(String speciesName, String pickupAddress) {
        Species species = speciesRepository.getSpeciesBySpeciesName(speciesName);
        Long speciesId = species.getSpeciesId();
        return catchRepository.findBySpecies_IdAndLocation(speciesId, pickupAddress);
    }

    public List<CatchViewDTO> getCatchesBySpeciesNameAndLocationAndPriceRange(String speciesName, String pickupAddress, BigDecimal minPrice, BigDecimal maxPrice) {
        return catchRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .filter(catchItem -> speciesName == null || catchItem.getSpecies().getSpeciesName().equalsIgnoreCase(speciesName))
                .filter(catchItem -> pickupAddress == null || catchItem.getPickupInfo().getAddress().equalsIgnoreCase(pickupAddress))
                .map(CatchMapper::toViewDTO)
                .collect(Collectors.toList());
    }
}
