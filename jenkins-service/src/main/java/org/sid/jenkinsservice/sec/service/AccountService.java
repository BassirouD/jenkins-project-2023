package org.sid.jenkinsservice.sec.service;

import org.sid.jenkinsservice.sec.entities.AppRole;
import org.sid.jenkinsservice.sec.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);

    AppRole addNewRole(AppRole appRole);

    void addRoleToUser(String username, String roleName);

    AppUser loadUserByUsername(String username);

    List<AppUser> listUsers();
}
