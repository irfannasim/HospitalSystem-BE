package com.sd.his.model;

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
 * @FileName  : BranchUser
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Entity
@Table(name = "BRANCH_USER")
public class BranchUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "IS_PRIMARY_BRANCH", columnDefinition = "boolean default true", nullable = false)
    private boolean primaryBranch;

    @Column(name = "IS_PRIMARY_DR", columnDefinition = "boolean default true", nullable = false)
    private boolean primaryDr;

    @Column(name = "IS_BILLING_BRANCH", columnDefinition = "boolean default true", nullable = false)
    private boolean billingBranch;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    public BranchUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPrimaryBranch() {
        return primaryBranch;
    }

    public void setPrimaryBranch(boolean primaryBranch) {
        this.primaryBranch = primaryBranch;
    }

    public boolean isPrimaryDr() {
        return primaryDr;
    }

    public void setPrimaryDr(boolean primaryDr) {
        this.primaryDr = primaryDr;
    }

    public boolean isBillingBranch() {
        return billingBranch;
    }

    public void setBillingBranch(boolean billingBranch) {
        this.billingBranch = billingBranch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
