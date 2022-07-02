package com.bgnc.userservice.controller;

import com.bgnc.userservice.model.Role;
import com.bgnc.userservice.model.User;
import com.bgnc.userservice.service.UserService;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class UserController {

    private  UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));

    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){

         URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));

    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser (@RequestBody RoleToUserForm form){

        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();


    }

}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}