package com.kazam.shopingcart.controller;


import com.kazam.shopingcart.model.Role;
import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public User addUser(@Valid @RequestBody User user){
        String pwd=user.getPassword();
        String encryptPWD=passwordEncoder.encode(pwd);
        user.setPassword(encryptPWD);
        return userService.addUser(user);
    }

    @PostMapping("/signUP")
    public User signUp(@RequestBody User user){
        Role role=new Role();
        role.setRole("USER");
        Set<Role> set = Stream.of(role).collect(Collectors.toSet());
        user.setRoles(set);
        String pwd=user.getPassword();
        String encryptPWD=passwordEncoder.encode(pwd);
        user.setPassword(encryptPWD);
        return userService.addUser(user);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }
}
