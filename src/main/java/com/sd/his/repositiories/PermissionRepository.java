package com.sd.his.repositiories;

import com.sd.his.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
List<Permission> findById(Integer id);
}
