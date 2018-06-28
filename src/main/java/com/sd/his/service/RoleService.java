package com.sd.his.service;

import com.sd.his.model.Permission;
import com.sd.his.model.Role;
import com.sd.his.model.RolePermission;
import com.sd.his.repositories.PermissionRepository;
import com.sd.his.repositories.RolePermissionRepository;
import com.sd.his.repositories.RoleRepository;
import com.sd.his.request.AssignAuthoritiesRequestWrapper;
import com.sd.his.request.RoleAndPermissionCreateRequest;
import com.sd.his.utill.HISCoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
 * @author    : Irfan Nasim
 * @Date      : 20-Apr-18
 * @version   : ver. 1.0.0
 * 
 * ________________________________________________________________________________________________
 *
 *  Developer				Date		     Version		Operation		Description
 * ________________________________________________________________________________________________ 
 *	
 * 
 * ________________________________________________________________________________________________
 *
 * @Project   : HIS
 * @Package   : com.sd.his.service
 * @FileName  : RoleService
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RolePermissionRepository rolePermissionRepository;
    @Autowired
    PermissionRepository permissionRepository;

    private final Logger logger = LoggerFactory.getLogger(RoleService.class);

    public List<Role> getAllActiveRoles() {
        return roleRepository.findAllByActiveTrueAndDeletedFalse();
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role save(RoleAndPermissionCreateRequest roleRequest) {
        Role role = new Role(roleRequest);
        return roleRepository.save(role);
    }

    @Transactional(rollbackOn = Throwable.class)
    public Boolean assignPermissionsToRole(AssignAuthoritiesRequestWrapper authRequest) {
        Boolean permissionAssigned;
        Role role = roleRepository.findByName(authRequest.getSelectedRole());
        if (HISCoreUtil.isValidObject(role)) {
            rolePermissionRepository.deleteAllByRole_Id(role.getId());
            List<RolePermission> rolePermissions = new ArrayList<>();
            List<Permission> selectedPermissions = permissionRepository.findByIdInAndActiveTrueAndDeletedFalse(authRequest.getPermissionIds());
            for (Permission per : selectedPermissions) {
                RolePermission rp = new RolePermission(role, per);
                rolePermissions.add(rp);
            }
            rolePermissionRepository.save(rolePermissions);
            permissionAssigned = true;
        } else {
            permissionAssigned = false;
        }
        return permissionAssigned;
    }

}
