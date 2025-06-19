package com.firstcatchcrew.restapi.role;


import com.firstcatchcrew.restapi.role.Role;
import com.firstcatchcrew.restapi.role.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        return roleOptional.orElse(null);
    }

    public List<Role> getRoleByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType);

    }

    public Role createRole(Role newRole) {
        return roleRepository.save(newRole);
    }

    public Role updateRole(long id, Role updatedRole) {
        Optional<Role> roleToUpdateOptional = roleRepository.findById(id);

        if (roleToUpdateOptional.isPresent()) {
            Role roleToUpdate = roleToUpdateOptional.get();

            //roleToUpdate.setUsername(updatedRole.getUsername());;

            return roleRepository.save(roleToUpdate);
        }

        return null;
    }

    public void deleteRoleById(long id) {
        roleRepository.deleteById(id);
    }

}
