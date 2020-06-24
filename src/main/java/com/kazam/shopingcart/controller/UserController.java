package com.kazam.shopingcart.controller;


import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
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
