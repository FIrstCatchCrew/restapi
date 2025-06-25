package com.firstcatchcrew.restapi.landing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandingRepository extends CrudRepository<Landing, Long> {
    List<Landing> findAll();
}
