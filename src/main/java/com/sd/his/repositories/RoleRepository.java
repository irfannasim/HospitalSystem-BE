package com.sd.his.repositories;

import com.sd.his.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findById(Integer id);

    Role findByName(String name);

    List<Role> findAllByActiveTrueAndDeletedFalse();

}
