package com.firstcatchcrew.restapi.fisherProfile;

import com.firstcatchcrew.restapi.fisherProfile.dto.FisherProfileCreateDTO;
import com.firstcatchcrew.restapi.fisherProfile.dto.FisherProfileViewDTO;
import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.person.Person;

public class FisherProfileMapper {

    public static FisherProfile toEntity(FisherProfileCreateDTO dto, Person person, Landing landing) {
        FisherProfile profile = new FisherProfile();
        profile.setPerson(person);
        profile.setFishingLicenseNumber(dto.getFishingLicenseNumber());
        profile.setDefaultLanding(landing);
        return profile;
    }

    public static FisherProfileViewDTO from(FisherProfile profile) {
        FisherProfileViewDTO dto = new FisherProfileViewDTO();
        dto.setId(profile.getId());
        dto.setFishingLicenseNumber(profile.getFishingLicenseNumber());
        dto.setUserName(profile.getPerson().getUsername());
        dto.setDefaultLanding(
                profile.getDefaultLanding() != null ? profile.getDefaultLanding().getName() : "No landing assigned"
        );
        return dto;
    }

}
