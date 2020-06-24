package com.kazam.shopingcart.service.impl;

import com.kazam.shopingcart.model.Role;
import com.kazam.shopingcart.repository.RoleRepository;
import com.kazam.shopingcart.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Role deleteRole(int id) {
        return null;
    }

    @Override
    public Role updateRole(int id) {
        return null;
    }
}
