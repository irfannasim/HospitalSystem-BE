package com.sd.his.request;

/*
 * @author    : Irfan Nasim
 * @Date      : 27-Apr-18
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
 * @Package   : com.sd.his.request
 * @FileName  : RoleAndPermissionUpdateRequest
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
public class RoleAndPermissionUpdateRequest {

    String name;
    String description;
    boolean status;
    String type;

    public RoleAndPermissionUpdateRequest() {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
