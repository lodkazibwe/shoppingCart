package com.kazam.shopingcart.controller;


import com.kazam.shopingcart.model.Role;
import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/addAdmin")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User userExists=userService.getUserByUserName(user.getUserName());
        if(userExists!=null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Role role=new Role();
        role.setRole("ADMIN");
        Set<Role> set = Stream.of(role).collect(Collectors.toSet());
        user.setRoles(set);
        String pwd=user.getPassword();
        String encryptPWD=passwordEncoder.encode(pwd);
        user.setPassword(encryptPWD);
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/addUser")
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
    @PostMapping("/addRoot")
    public ResponseEntity<User> rootUser(@Valid @RequestBody User user){
        User root=userService.getUserByUserName("Root");
        if(root==null){
            Role role=new Role();
            role.setRole("ROOT");
            Set<Role> set = Stream.of(role).collect(Collectors.toSet());
            user.setRoles(set);
            user.setUserName("Root");
            String pwd=user.getPassword();
            String encryptPWD=passwordEncoder.encode(pwd);
            user.setPassword(encryptPWD);
            return new ResponseEntity<>(userService.addUser(user),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

   @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }
}
