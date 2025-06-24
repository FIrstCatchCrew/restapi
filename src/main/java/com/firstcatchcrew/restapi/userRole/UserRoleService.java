package com.firstcatchcrew.restapi.userRole;

import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public void assignRoleFromString(UserRole userRole, String input) {
        try {
            UserRoleType type = UserRoleType.valueOf(input.toUpperCase());
            userRole.setType(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role type: " + input + ". Allowed: " + Arrays.toString(UserRoleType.values()));
        }
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

    public boolean deleteUserRoleById(long id) {
        if (!userRoleRepository.existsById(id)) return false;
        userRoleRepository.deleteById(id);
        return true;
    }
}
