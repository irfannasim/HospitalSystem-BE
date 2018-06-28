package com.sd.his.repositories;

import com.sd.his.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findById(Integer id);

    List<Permission> findAllByActiveTrueAndDeletedFalse();

    @Query("SELECT DISTINCT p FROM Permission p INNER JOIN p.roles rp INNER JOIN rp.role r INNER JOIN r.users ur INNER JOIN ur.user u WHERE u.id=:userId")
    List<Permission> findAllUserRolePermissions(@Param("userId") long userId);

    @Query("SELECT p FROM Permission p INNER JOIN p.users up WHERE up.user.id = :userId")
    List<Permission> findAllUserPermissions(@Param("userId") long userId);

    Permission findByName(String name);

    @Query("SELECT p FROM Permission p INNER JOIN p.roles pr  WHERE pr.role.id = :roleId")
    List<Permission> findByRoles(@Param("roleId") long roleId);

    List<Permission> findByIdInAndActiveTrueAndDeletedFalse(List<Long> ids);

}
