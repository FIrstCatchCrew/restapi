package com.firstcatchcrew.restapi.userRole;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserRole getByType(UserRoleType roleType) {
        return userRoleRepository.findByType(roleType);
    }

    @Transactional
    public UserRole createUserRole(UserRole newUserRole) {
        return userRoleRepository.save(newUserRole);
    }

    @Transactional
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
