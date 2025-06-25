package com.firstcatchcrew.restapi.fisherProfile;

import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import com.firstcatchcrew.restapi.fishCatch.mapper.CatchMapper;
import com.firstcatchcrew.restapi.fisherProfile.dto.FisherProfileViewDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class FisherProfileService {

    private final FisherProfileRepository fisherRepository;

    public FisherProfileService(FisherProfileRepository fisherRepository) {
        this.fisherRepository = fisherRepository;
    }

    public List<FisherProfileViewDTO> getAllFishers() {
        return StreamSupport
                .stream(fisherRepository.findAll().spliterator(), false)
                .map(FisherProfileMapper::from)
                .toList();
    }

    public FisherProfileViewDTO getFisherById(long id) {
        return fisherRepository.findById(id)
                .map(FisherProfileMapper::from)
                .orElse(null);
    }


    public List<CatchViewDTO> getCatchesByFisherId(long fisherId) {
        return fisherRepository.findById(fisherId)
                .map(FisherProfile::getCatches)
                .orElse(List.of())
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getSoldCatchesByFisherId(long fisherId) {
        return fisherRepository.findById(fisherId)
                .map(FisherProfile::getCatches)
                .orElse(List.of())
                .stream()
                .filter(Catch::isSold)
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getExpiredUnsoldCatchesByFisherId(long fisherId) {
        return fisherRepository.findById(fisherId)
                .map(FisherProfile::getCatches)
                .orElse(List.of())
                .stream()
                .filter(c -> !c.isAvailable() && !c.isSold())
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    @Transactional
    public FisherProfile createFisher(FisherProfile newFisher) {
        return fisherRepository.save(newFisher);
    }

    @Transactional
    public FisherProfile updateFisher(long id, FisherProfile updatedFisher) {
        return fisherRepository.findById(id)
                .map(existing -> {
                    existing.setPerson(updatedFisher.getPerson());
                    existing.setFishingLicenseNumber(updatedFisher.getFishingLicenseNumber());
                    existing.setDefaultLanding(updatedFisher.getDefaultLanding());
                    return fisherRepository.save(existing);
                }).orElse(null);
    }

    public boolean deleteFisherById(long id) {
        if (!fisherRepository.existsById(id)) return false;
        fisherRepository.deleteById(id);
        return true;
    }
}
