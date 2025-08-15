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

@Component
public class CatchMapper {

    public CatchMapper() { }

    public static Catch fromCreateDTO(CatchCreateDTO dto, FisherProfile fisher, Species species, Landing landing) {
        Catch fishCatch = new Catch();

        fishCatch.setFisher(fisher);
        fishCatch.setSpecies(species);
        fishCatch.setLanding(landing); // ADD THIS LINE
        fishCatch.setQuantityInKg(dto.getQuantityInKg());
        fishCatch.setPrice(dto.getPrice());

        fishCatch.setTimeStamp(
                dto.getCatchDate() != null ? dto.getCatchDate() : java.time.LocalDateTime.now()
        );

        fishCatch.setGeoLocation(new GeoLocation(
                dto.getLatitude(),
                dto.getLongitude()
        ));

        fishCatch.setPickupInfo(new PickupInfo(
                dto.getPickupInstructions(),
                dto.getPickupTime()
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
