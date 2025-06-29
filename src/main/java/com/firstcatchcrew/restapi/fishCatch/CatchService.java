package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.dto.CatchCreateDTO;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import com.firstcatchcrew.restapi.fishCatch.embedded.GeoLocation;
import com.firstcatchcrew.restapi.fishCatch.embedded.PickupInfo;
import com.firstcatchcrew.restapi.fishCatch.mapper.CatchMapper;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfileRepository;
import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.landing.LandingRepository;
import com.firstcatchcrew.restapi.species.Species;
import com.firstcatchcrew.restapi.species.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CatchService {
    private final CatchRepository catchRepository;
    private final FisherProfileRepository fisherRepository;
    private final SpeciesRepository speciesRepository;
    private final LandingRepository landingRepository;

    public CatchService(CatchRepository catchRepository, FisherProfileRepository fisherRepository, SpeciesRepository speciesRepository, LandingRepository landingRepository) {
        this.catchRepository = catchRepository;
        this.fisherRepository = fisherRepository;
        this.speciesRepository = speciesRepository;
        this.landingRepository = landingRepository;
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

    public List<CatchViewDTO> getAvailableCatchesByFisherName(String username) {
        return catchRepository.findByFisher_Person_UsernameAndAvailableTrue(username)
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

    public List<CatchViewDTO> getCatchesByLocation(String landingName) {
        Landing landing = landingRepository.getLandingByName(landingName);
        Long landingId = landing.getId();
        return catchRepository.findByLanding_Id(landingId)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getCatchesBySpeciesName(String speciesName) {
        Species species = speciesRepository.getSpeciesByName(speciesName);
        Long speciesId = species.getId();
        return catchRepository.findBySpecies_Id(speciesId)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getCatchesBySpeciesId(Long speciesId) {
        return catchRepository.findBySpecies_Id(speciesId)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public List<CatchViewDTO> getCatchesBySpeciesNameAndLocation(String speciesName, String landingName) {
        Species species = speciesRepository.getSpeciesByName(speciesName);
        Long speciesId = species.getId();
        Landing landing = landingRepository.getLandingByName(landingName);
        Long landingId = landing.getId();
        return catchRepository.findBySpecies_IdAndLanding_Id(speciesId, landingId)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    public Map<String, Set<String>> getAvailableSpeciesByLanding() {
        return catchRepository.findByAvailableTrue().stream()
                .collect(Collectors.groupingBy(
                        catchObj -> catchObj.getLanding().getName(), // or .getLanding().getName() if available
                        Collectors.mapping(catchObj -> catchObj.getSpecies().getName(), Collectors.toSet())
                ));
    }

    public List<CatchViewDTO> getCatchesBySpeciesNameAndLocationAndPriceRange(
            String speciesName,
            String landingName,
            BigDecimal minPrice,
            BigDecimal maxPrice) {

        if (speciesName == null && landingName == null) {
            return getCatchesByPriceRange(minPrice, maxPrice);
        }

        return catchRepository
                .findByPriceBetweenAndSpecies_NameIgnoreCaseAndLanding_NameIgnoreCase(
                        minPrice, maxPrice, speciesName, landingName)
                .stream()
                .map(CatchMapper::toViewDTO)
                .toList();
    }

    @Transactional
    public CatchViewDTO createCatch(CatchCreateDTO dto) {
        FisherProfile fisher = fisherRepository.findById(dto.getFisherId())
                .orElseThrow(() -> new IllegalArgumentException("Fisher with id " + dto.getFisherId() + " not found."));
        Species species = speciesRepository.findById(dto.getSpeciesId())
                .orElseThrow(() -> new IllegalArgumentException("Species with id " + dto.getSpeciesId() + " not found."));

        Catch newCatch = CatchMapper.fromCreateDTO(dto, fisher, species);
        newCatch.refreshAvailability();
        return CatchMapper.toViewDTO(catchRepository.save(newCatch));
    }

    @Transactional
    public CatchViewDTO updateCatch(long id, CatchCreateDTO dto) {
        Optional<Catch> catchToUpdateOptional = catchRepository.findById(id);
        if (catchToUpdateOptional.isEmpty()) return null;

        Catch catchToUpdate = catchToUpdateOptional.get();

        // Re-fetch relationships
        FisherProfile fisher = fisherRepository.findById(dto.getFisherId())
                .orElseThrow(() -> new IllegalArgumentException("Fisher not found: " + dto.getFisherId()));
        Species species = speciesRepository.findById(dto.getSpeciesId())
                .orElseThrow(() -> new IllegalArgumentException("Species not found: " + dto.getSpeciesId()));
        Landing landing = landingRepository.findById(dto.getLandingId())
                .orElseThrow(() -> new IllegalArgumentException("Landing not found: " + dto.getLandingId()));

        // Update fields
        catchToUpdate.setFisher(fisher);
        catchToUpdate.setSpecies(species);
        catchToUpdate.setPrice(dto.getPrice());
        catchToUpdate.setQuantityInKg(dto.getQuantityInKg());
        catchToUpdate.setTimeStamp(dto.getCatchDate());
        catchToUpdate.setLanding(landing);

        catchToUpdate.setGeoLocation(new GeoLocation(dto.getLatitude(), dto.getLongitude()));
        catchToUpdate.setPickupInfo(new PickupInfo(dto.getPickupInstructions(), dto.getPickupTime()));

        catchToUpdate.refreshAvailability();

        return CatchMapper.toViewDTO(catchRepository.save(catchToUpdate));
    }

    @Transactional
    public void refreshAvailabilityForAllCatches() {
        List<Catch> allCatches = catchRepository.findAll();
        allCatches.forEach(Catch::refreshAvailability);
        catchRepository.saveAll(allCatches);
    }

    public boolean deleteCatchById(long id) {
        if (!catchRepository.existsById(id)) return false;
        catchRepository.deleteById(id);
        return true;
    }
}
