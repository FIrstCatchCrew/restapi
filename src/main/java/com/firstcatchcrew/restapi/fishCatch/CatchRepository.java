package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.orderItem.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatchRepository extends CrudRepository<Catch, Long> {
    List<Catch> findAll();

    List<Catch> findByAvailableTrue(); // uses Spring Data’s keyword parsing to find all with available = true.

    List<Catch> findByFisher(FisherProfile fisher); // uses the actual FisherProfile object —
    List<Catch> findByFisher_Id(Long id);

    List<Catch> findByFisherAndOrderItemIsNotNull(FisherProfile fisher); // gives you sold catches only.
    List<Catch> findByFisherIdAndOrderItemIsNotNull(Long fisherId);
}