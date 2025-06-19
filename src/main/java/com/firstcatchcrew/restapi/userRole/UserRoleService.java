package com.firstcatchcrew.restapi.userRole;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public UserRole getUserRoleById(long id) {
        Optional<UserRole> roleOptional = userRoleRepository.findById(id);

        return roleOptional.orElse(null);
    }

    public List<UserRole> getUserRoleByRoleType(UserRoleType userRoleType) {
        return userRoleRepository.findByUserRoleType(userRoleType);

    }

    public UserRole createUserRole(UserRole newUserRole) {
        return userRoleRepository.save(newUserRole);
    }

    public UserRole updateUserRole(long id, UserRole updatedUserRole) {
        Optional<UserRole> roleToUpdateOptional = userRoleRepository.findById(id);

        if (roleToUpdateOptional.isPresent()) {
            UserRole userRoleToUpdate = roleToUpdateOptional.get();

            userRoleToUpdate.setDescription(updatedUserRole.getDescription());
            userRoleToUpdate.setLabel(updatedUserRole.getLabel());
            userRoleToUpdate.setType(updatedUserRole.getType());             //CLEANUP: userRoleToUpdate.setTypeFromString(updatedUserRole.getType().toString());

            return userRoleRepository.save(userRoleToUpdate);
        }
        return null;
    }

    public void deleteUserRoleById(long id) {
        userRoleRepository.deleteById(id);
    }

}
