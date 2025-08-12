// src/main/java/com/firstcatchcrew/restapi/security/UserPrincipal.java
package com.firstcatchcrew.restapi.security;

import com.firstcatchcrew.restapi.person.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final Long id;
    private final String email;
    private final String passwordHash;
    private final List<GrantedAuthority> authorities;
    private final boolean enabled;

    public static UserPrincipal from(Person person) {
        String roleName = "ROLE_" + person.getRole().getType().name();
        return new UserPrincipal(
                person.getId(),
                person.getEmail(),
                person.getPasswordHash(),
                List.of(new SimpleGrantedAuthority(roleName)),
                person.isEnabled()
        );
    }

    private UserPrincipal(Long id, String email, String passwordHash,
                          List<GrantedAuthority> authorities, boolean enabled) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public Long getId() { return id; }
    @Override public String getUsername() { return email; }   // we log in with email
    @Override public String getPassword() { return passwordHash; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public boolean isEnabled() { return enabled; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
}
