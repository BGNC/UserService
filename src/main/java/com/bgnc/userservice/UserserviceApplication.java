package com.bgnc.userservice;

import com.bgnc.userservice.model.Role;
import com.bgnc.userservice.model.User;
import com.bgnc.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@SpringBootApplication
@Component
public class UserserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserserviceApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));


            userService.saveUser(new User(null,"John Travolta","john","1234",new ArrayList<>()));

            userService.saveUser(new User(null,"Will Smith","will","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Tom Jerry","tom","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Arnold Veli","arnold","1234",new ArrayList<>()));

            userService.addRoleToUser("john","ROLE_USER");
            userService.addRoleToUser("john","ROLE_MANAGER");
            userService.addRoleToUser("will","ROLE_MANAGER");
            userService.addRoleToUser("tom","ROLE_ADMIN");
            userService.addRoleToUser("arnold","ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold","ROLE_ADMIN");
            userService.addRoleToUser("arnold","ROLE_USER");



        };

    }
}
