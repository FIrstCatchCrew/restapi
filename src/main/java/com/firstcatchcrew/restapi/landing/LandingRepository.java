package com.firstcatchcrew.restapi.landing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingRepository extends CrudRepository<Landing, Long> {
}
