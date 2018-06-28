package com.sd.his.model;/*
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
 * @Package   : com.sd.his.*
 * @FileName  : UserAuthAPI
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEPARTMENT_USER")
public class DepartmentUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DEPART_ID")
    private ClinicalDepartment clinicalDepartment;

    public DepartmentUser() {
    }

    public DepartmentUser(boolean deleted, long updatedOn, long createdOn, User user, ClinicalDepartment clinicalDepartment) {
        this.deleted = deleted;
        this.updatedOn = updatedOn;
        this.createdOn = createdOn;
        this.user = user;
        this.clinicalDepartment = clinicalDepartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClinicalDepartment getClinicalDepartment() {
        return clinicalDepartment;
    }

    public void setClinicalDepartment(ClinicalDepartment clinicalDepartment) {
        this.clinicalDepartment = clinicalDepartment;
    }
}