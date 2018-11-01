package com.sadsoft.communicator.dao;

import com.sadsoft.communicator.model.Role;
import com.sadsoft.communicator.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRolename(RoleName rolename);
}
