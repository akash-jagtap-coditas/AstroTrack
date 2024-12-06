package com.example.AstroTrack.config;

import com.example.AstroTrack.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of {@link UserDetails} interface for user authentication.
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;


    private final String userName;

    @JsonIgnore
    private final String password;

    /**
     * Constructs a new UserDetailsImpl with the specified username and password.
     *
     * @param userName the username
     * @param password the password
     */
    public UserDetailsImpl(String userName, String password) {
        // this.id = id;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Builds a UserDetailsImpl from a Users entity.
     *
     * @param user the Users entity
     * @return a UserDetailsImpl instance
     */
    public static UserDetailsImpl build(Users user) {
        return new UserDetailsImpl(
                // user.getId(),
                user.getUsername(),
                user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(userName, user.userName);
    }
}
