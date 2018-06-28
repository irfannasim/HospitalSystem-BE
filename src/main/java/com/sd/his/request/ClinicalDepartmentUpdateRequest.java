package com.sd.his.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/*
 * @author    : Irfan Nasim
 * @Date      : 4/10/2018
 * @version   : ver. 1.0.0
 *
 * ________________________________________________________________________________________________
 *
 *  Developer    Date       Version  Operation  Description
 * ________________________________________________________________________________________________
 *
 *
 * ________________________________________________________________________________________________
 *
 * @Project   : HIS
 * @Package   : com.sd.his.request
 * @FileName  : ClinicalDepartmentUpdateRequest
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@JsonIgnoreProperties
public class ClinicalDepartmentUpdateRequest {

    private long id;
    private String name;
    private String description;
    private boolean active;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}