package com.firstcatchcrew.restapi.fishCatch.mapper;
import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchCreateDTO;
import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import com.firstcatchcrew.restapi.fishCatch.embedded.GeoLocation;
import com.firstcatchcrew.restapi.fishCatch.embedded.PickupInfo;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.species.Species;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CatchMapper {

    public CatchMapper() { }

    public static Catch fromCreateDTO(CatchCreateDTO dto, FisherProfile fisher, Species species, Landing landing) {
        Catch fishCatch = new Catch();

        fishCatch.setFisher(fisher);
        fishCatch.setSpecies(species);
        fishCatch.setLanding(landing);
        fishCatch.setQuantityInKg(dto.getQuantityInKg());
        fishCatch.setPrice(dto.getPrice());

        LocalDateTime catchDate = dto.getCatchDate() != null
                ? dto.getCatchDate()
                : LocalDateTime.now();

        fishCatch.setTimeStamp(catchDate);

        // CHORE: CHANGE ME -- implement functionality for fisher to set this
        // Pickup time: always 3 hours after catch
        LocalDateTime pickupTime = catchDate.plusHours(3);


        // CHORE: CHANGE ME -- implement automatic geo tagging
        fishCatch.setGeoLocation(new GeoLocation(
                dto.getLatitude(),
                dto.getLongitude()
        ));


        fishCatch.setPickupInfo(new PickupInfo(
                dto.getPickupInstructions() != null ? dto.getPickupInstructions() : "No specific instructions",
                pickupTime
        ));

        return fishCatch;
    }

    public static CatchViewDTO toViewDTO (Catch fishCatch){
        CatchViewDTO dto = new CatchViewDTO();

        dto.setId(fishCatch.getId());
        dto.setFisherName(fishCatch.getFisher().getPerson().getUsername());
        dto.setSpeciesName(fishCatch.getSpecies().getName());
        dto.setLandingName(fishCatch.getLanding().getName());
        dto.setTimeStamp(fishCatch.getTimeStamp());
        dto.setQuantityInKg(fishCatch.getQuantityInKg());
        dto.setPrice(fishCatch.getPrice());
        dto.setAvailable(fishCatch.isAvailable());

        PickupInfo pickup = fishCatch.getPickupInfo();
        if (pickup != null) {
            dto.setPickupInstructions(pickup.getInstructions());
            dto.setPickupTime(pickup.getPickupTime());
        }

        GeoLocation geo = fishCatch.getGeoLocation();
        if (geo != null) {
            dto.setLatitude(geo.getLatitude());
            dto.setLongitude(geo.getLongitude());
        }

        return dto;
    }
}
