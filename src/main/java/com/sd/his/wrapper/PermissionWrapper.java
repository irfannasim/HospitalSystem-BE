package com.sd.his.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.model.Permission;

/*
 * @author    : waqas kamran
 * @Date      : 17-Apr-18
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
 * @Package   : com.sd.his.wrapper
 * @FileName  : PermissionWrapper
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionWrapper {
    long id;
    String name;
    String description;
    boolean deleted;
    boolean active;

    public PermissionWrapper() {
    }

    public PermissionWrapper(Permission permission) {
        this.id = permission.getId();
        this.name = permission.getName();
        this.description = permission.getDescription();
        this.deleted = permission.isDeleted();
        this.active = permission.isActive();

    }

    public PermissionWrapper(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PermissionWrapper(long id, String name, String description, boolean deleted, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}