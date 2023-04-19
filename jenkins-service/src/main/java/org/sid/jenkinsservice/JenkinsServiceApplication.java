package org.sid.jenkinsservice;

import org.sid.jenkinsservice.sec.entities.AppRole;
import org.sid.jenkinsservice.sec.entities.AppUser;
import org.sid.jenkinsservice.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class JenkinsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JenkinsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            String user1 = "user1";
            String user2 = "user2";
            String user3 = "user3";
            String user4 = "user4";
            String admin = "admin";

            accountService.addNewRole(new AppRole(null, "USER"));
            accountService.addNewRole(new AppRole(null, "ADMIN"));
            accountService.addNewRole(new AppRole(null, "CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRole(null, "PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRole(null, "BILLS_MANAGER"));

            accountService.addNewUser(new AppUser(null, user1, "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, user2, "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, user3, "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, user4, "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, admin, "1234", new ArrayList<>()));

            accountService.addRoleToUser(user1, "USER");

            accountService.addRoleToUser(user2, "USER");
            accountService.addRoleToUser(user2, "CUSTOMER_MANAGER");

            accountService.addRoleToUser(user3, "USER");
            accountService.addRoleToUser(user3, "PRODUCT_MANAGER");

            accountService.addRoleToUser(user4, "USER");
            accountService.addRoleToUser(user3, "BILLS_MANAGER");

            accountService.addRoleToUser(admin, "USER");
            accountService.addRoleToUser(admin, "ADMIN");

        };
    }

}
