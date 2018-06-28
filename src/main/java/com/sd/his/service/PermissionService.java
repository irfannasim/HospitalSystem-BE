package com.sd.his.service;

import com.sd.his.model.Permission;
import com.sd.his.model.Role;
import com.sd.his.repositories.PermissionRepository;
import com.sd.his.request.RoleAndPermissionCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
 * @FileName  : PermissionService
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    private final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    public List<Permission> getAllActivePermissions() {
        return permissionRepository.findAllByActiveTrueAndDeletedFalse();
    }

    public Permission getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }

    public Permission save(RoleAndPermissionCreateRequest permissionRequest) {
        Permission permission = new Permission(permissionRequest);
        return permissionRepository.save(permission);
    }

    public List<Permission> getPermissionByRole(long roleId){
         return permissionRepository.findByRoles(roleId);
    }

}
