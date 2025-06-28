package com.firstcatchcrew.restapi.species;

import jakarta.persistence.*;

@Entity
public class Species {
    @Id
    @SequenceGenerator(name = "species_sequence", sequenceName = "species_sequence", allocationSize = 1)
    @GeneratedValue(generator = "species_sequence")
    private Long id;

    @Column(name = "species_name")
    private String name;
    private String description;
    private String imageUrl;
    private String infoLink;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
