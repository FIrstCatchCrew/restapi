package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.userRole.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByRole(UserRole role);
    List<Person> findAll();


}
