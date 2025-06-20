package com.firstcatchcrew.restapi.fishCatch.mapper;
import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchCreateDTO;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import com.firstcatchcrew.restapi.fishCatch.embedded.GeoLocation;
import com.firstcatchcrew.restapi.fishCatch.embedded.PickupInfo;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.species.Species;

public class CatchMapper {

    public static Catch toEntity(CatchCreateDTO dto, FisherProfile fisher, Species species) {
        Catch fishCatch = new Catch();

        fishCatch.setFisher(fisher);
        fishCatch.setSpecies(species);
        fishCatch.setCatchDate(dto.getCatchDate());
        fishCatch.setQuantityInKg(dto.getQuantityInKg());
        fishCatch.setPrice(dto.getPrice());

        fishCatch.setPickupInfo(new PickupInfo(
                dto.getPickupLocationName(),
                dto.getPickupAddress(),
                dto.getPickupTime()
        ));

        fishCatch.setGeoLocation(new GeoLocation(
                dto.getLatitude(),
                dto.getLongitude()
        ));

        return fishCatch;
    }

    public static CatchViewDTO toViewDTO(Catch fishCatch) {

        // CLEANUP: protects against having something sold and available
        boolean available = fishCatch.isAvailable();
        boolean sold = fishCatch.isSold();

        validateCatchStatus(sold, available);

        CatchViewDTO dto = new CatchViewDTO();

        dto.setId(fishCatch.getId());
        dto.setFisherName(fishCatch.getFisher().getPerson().getUsername()); // Or getFullName()
        dto.setSpeciesName(fishCatch.getSpecies().getSpeciesName());

        dto.setCatchDate(fishCatch.getCatchDate());
        dto.setQuantityInKg(fishCatch.getQuantityInKg());
        dto.setPrice(fishCatch.getPrice());
        dto.setAvailable(available);
        dto.setSold(sold);

        PickupInfo pickup = fishCatch.getPickupInfo();
        if (pickup != null) {
            dto.setPickupLocationName(pickup.getLocationName());
            dto.setPickupAddress(pickup.getAddress());
            dto.setPickupTime(pickup.getPickupTime());
        }

        GeoLocation geo = fishCatch.getGeoLocation();
        if (geo != null) {
            dto.setLatitude(geo.getLatitude());
            dto.setLongitude(geo.getLongitude());
        }

        return dto;
    }

    // CLEANUP: HELPER FUNCTION protects against having something sold and available
    private static void validateCatchStatus(boolean sold, boolean available) {
        if (sold && available) {
            throw new IllegalStateException("A catch cannot be both sold and available.");
        }
    }
}
