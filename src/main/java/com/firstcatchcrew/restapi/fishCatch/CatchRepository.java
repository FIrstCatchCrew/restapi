package com.firstcatchcrew.restapi.fishCatch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatchRepository extends CrudRepository<Catch, Long> {
}
