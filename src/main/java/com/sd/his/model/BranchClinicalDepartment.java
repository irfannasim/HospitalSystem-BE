package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

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
 * @FileName  : BranchClinicalDepartment
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Entity
@Table(name = "BRANCH_CLINICAL_DPT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchClinicalDepartment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "CLI_DPT_ID")
    private ClinicalDepartment clinicalDpt;

    public BranchClinicalDepartment() {
    }

    public BranchClinicalDepartment(Branch branch, ClinicalDepartment dpt) {
        this.branch = branch;
        this.clinicalDpt = dpt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public ClinicalDepartment getClinicalDpt() {
        return clinicalDpt;
    }

    public void setClinicalDpt(ClinicalDepartment clinicalDpt) {
        this.clinicalDpt = clinicalDpt;
    }
}
