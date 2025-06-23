package com.firstcatchcrew.restapi.species;

import jakarta.persistence.*;

@Entity
public class Species {
    @Id
    @SequenceGenerator(name = "species_sequence", sequenceName = "species_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "species_sequence")
    private Long speciesId;
    private String speciesName;
    private String description;
    private String imageUrl;
    private String infoLink;  //image, external link to information about species, alternative names?

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    @Override
    public String toString() {
        return "Species{" +
                "id=" + speciesId +
                ", name='" + speciesName + '\'' +
                '}';
    }
}
