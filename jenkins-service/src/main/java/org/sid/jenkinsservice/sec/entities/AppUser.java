package org.sid.jenkinsservice.sec.entities;

import jakarta.persistence.Entity;

import java.util.Collection;

@Entity
public class AppUser {
    private Long id;
    private String username;
    private String password;
    private Collection<AppRole> appRoles;
}
