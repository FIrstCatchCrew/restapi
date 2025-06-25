package com.firstcatchcrew.restapi.userRole;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    UserRole findByType(UserRoleType type);
    List<UserRole> findAll();
    UserRole findByTypeString(String type);


}
