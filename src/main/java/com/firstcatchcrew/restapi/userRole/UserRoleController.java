package com.firstcatchcrew.restapi.userRole;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/role")
@CrossOrigin
public class UserRoleController {
    private final UserRoleService service;

    public UserRoleController(UserRoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserRole>> getAll() {
        return ResponseEntity.ok(service.getAllUserRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> getById(@PathVariable long id) {
        UserRole role = service.getUserRoleById(id);
        return (role != null) ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<UserRole> getByType(@PathVariable String type) {
        try {
            UserRoleType roleType = UserRoleType.valueOf(type.toUpperCase());
            UserRole role = service.getByType(roleType);
            return (role != null) ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // or return a message
        }
    }

    @PostMapping
    public ResponseEntity<UserRole> create(@RequestBody UserRole role) {
        UserRole saved = service.createUserRole(role);
        return ResponseEntity.created(URI.create("/api/roles/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRole> update(@PathVariable long id, @RequestBody UserRole updated) {
        UserRole result = service.updateUserRole(id, updated);
        return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return service.deleteUserRoleById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}