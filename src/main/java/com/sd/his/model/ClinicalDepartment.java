package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.request.ClinicalDepartmentCreateRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 * @author    : Irfan Nasim
 * @Date      : 24-Apr-18
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
 * @Package   : com.sd.his.model
 * @FileName  : ClinicalDepartment
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Entity
@Table(name = "CLINICAL_DEPARTMENT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalDepartment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default false", nullable = false)
    private boolean active;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @JsonIgnore
    @OneToMany(targetEntity = BranchClinicalDepartment.class, mappedBy = "clinicalDpt", fetch = FetchType.LAZY)
    private List<BranchClinicalDepartment> branches;

    @JsonIgnore
    @OneToMany(targetEntity = ClinicalDepartmentMedicalService.class, mappedBy = "clinicalDpt", fetch = FetchType.LAZY)
    private List<ClinicalDepartmentMedicalService> medicalServices;

    @JsonIgnore
    @OneToMany(targetEntity = DepartmentUser.class, mappedBy = "clinicalDepartment", fetch = FetchType.LAZY)
    private List<DepartmentUser> users;

    public ClinicalDepartment() {
    }

    public ClinicalDepartment(ClinicalDepartmentCreateRequest createRequest) {
        this.id = createRequest.getDepartmentId();
        this.name = createRequest.getName();
        this.description = createRequest.getDescription();
        this.active = createRequest.isActive();
        this.deleted = false;
        this.createdOn = System.currentTimeMillis();
        this.updatedOn = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "ClinicalDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
    }

    public List<DepartmentUser> getUsers() {
        return users;
    }

    public void setUsers(List<DepartmentUser> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<BranchClinicalDepartment> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchClinicalDepartment> branches) {
        this.branches = branches;
    }

    public List<ClinicalDepartmentMedicalService> getMedicalServices() {
        return medicalServices;
    }

    public void setMedicalServices(List<ClinicalDepartmentMedicalService> medicalServices) {
        this.medicalServices = medicalServices;
    }
}
