package com.bgnc.userservice.service;

import com.bgnc.userservice.model.Role;
import com.bgnc.userservice.model.User;
import com.bgnc.userservice.repository.RoleRepository;
import com.bgnc.userservice.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Slf4j
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService{

    private  UserRepository userRepository;
    private  RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User saveUser(User user) {

        log.info("Saving a new user with {} in database",user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving a new role with {} in database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        log.info("Adding role {} to user {}",roleName,username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("Fetching to user {}",username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}
