package com.kazam.shopingcart.service;

import com.kazam.shopingcart.model.Role;

public interface RoleService {
    Role addRole(Role role);
    Role deleteRole(int id);
    Role updateRole(int id);
}
