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
        return userRoleRepository.findById(id).orElse(null);

    }

    public UserRole getByType(UserRoleType type) {
        return userRoleRepository.findByType(type);
    }

//    public List<UserRole> getUserRoleByType(UserRoleType type) {
//        return userRoleRepository.findByType(type);
//    }

    public UserRole createUserRole(UserRole newUserRole) {
        return userRoleRepository.save(newUserRole);
    }

    public UserRole updateUserRole(long id, UserRole updatedUserRole) {
        return userRoleRepository.findById(id).map(existing -> {
            existing.setDescription(updatedUserRole.getDescription());
            existing.setLabel(updatedUserRole.getLabel());
            existing.setType(updatedUserRole.getType());
            return userRoleRepository.save(existing);
        }).orElse(null);
    }

    public void deleteUserRoleById(long id) {
        userRoleRepository.deleteById(id);
    }
}
