package com.kazam.shopingcart.controller;


import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }
}
