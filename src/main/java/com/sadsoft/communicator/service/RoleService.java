package com.sadsoft.communicator.service;

import com.sadsoft.communicator.dao.RoleRepository;
import com.sadsoft.communicator.model.Role;
import com.sadsoft.communicator.model.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private RoleName[] roles = RoleName.values();

    public void generateRoles() {

        Role role;

        for (RoleName roleName: roles) {
            role = new Role();
            role.setRolename(roleName);
            roleRepository.save(role);
        }
    }
}
