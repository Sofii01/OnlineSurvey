package com.online.survey.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.online.survey.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Boolean deleted = false;

    private Role role;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;

    public UserModel(String email, String password, String name, String lastName, Boolean deleted, Role role, LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.deleted = deleted;
        this.role = role;
        this.createdAt = createdAt;
    }
}
