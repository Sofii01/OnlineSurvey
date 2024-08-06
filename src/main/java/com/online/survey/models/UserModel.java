package com.online.survey.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.online.survey.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String lastName;
    private String email;
    private String password;
    private Boolean deleted = false;
    @Enumerated(EnumType.STRING)
    private Role role = Role.Surveyed;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt = LocalDateTime.now();

    public UserModel(String email, String password, String name, String lastName, Boolean deleted, Role role, LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.username = name;
        this.lastName = lastName;
        this.deleted = deleted;
        this.role = role;
        this.createdAt = createdAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
