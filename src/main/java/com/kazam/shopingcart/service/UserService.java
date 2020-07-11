package com.kazam.shopingcart.service;

import com.kazam.shopingcart.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
    User getUserByAddress(String address);
    User getUserByUserName(String userName);
    User deleteUser(int id);
    User updateUser(int id);

}
