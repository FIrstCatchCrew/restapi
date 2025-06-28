package com.firstcatchcrew.restapi.fisherProfile;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FisherProfileRepository extends CrudRepository<FisherProfile, Long> {

}
