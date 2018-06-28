package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
 * @Package   : com.sd.his.DutyShift
 * @FileName  : DutyShift
 *
 * Copyright © 
 * SolutionDots, 
 * All rights reserved.
 * 
 */
@Entity
@Table(name = "DUTY_SHIFT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DutyShift implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "START_TIME_SHIFT1")
    private String startTimeShift1;

    @Column(name = "END_TIME_SHIFT1")
    private String endTimeShift1;

    @Column(name = "START_TIME_SHIFT2")
    private String startTimeShift2;

    @Column(name = "END_TIME_SHIFT2")
    private String endTimeShift2;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @Column(name = "DUTY_TIMMING_SHIFT_1", columnDefinition = "boolean default false")
    private Boolean dutyTimmingShift1;

    @Column(name = "DUTY_TIMMING_SHIFT_2", columnDefinition = "boolean default false")
    private Boolean dutyTimmingShift2;


    @JsonIgnore
    @OneToMany(targetEntity = UserDutyShift.class, mappedBy = "dutyShift", fetch = FetchType.LAZY)
    private List<UserDutyShift> users;

    public DutyShift() {
    }

    public Boolean getDutyTimmingShift1() {
        return dutyTimmingShift1;
    }

    public void setDutyTimmingShift1(Boolean dutyTimmingShift1) {
        this.dutyTimmingShift1 = dutyTimmingShift1;
    }

    public Boolean getDutyTimmingShift2() {
        return dutyTimmingShift2;
    }

    public void setDutyTimmingShift2(Boolean dutyTimmingShift2) {
        this.dutyTimmingShift2 = dutyTimmingShift2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTimeShift1() {
        return startTimeShift1;
    }

    public void setStartTimeShift1(String startTimeShift1) {
        this.startTimeShift1 = startTimeShift1;
    }

    public String getEndTimeShift1() {
        return endTimeShift1;
    }

    public void setEndTimeShift1(String endTimeShift1) {
        this.endTimeShift1 = endTimeShift1;
    }

    public String getStartTimeShift2() {
        return startTimeShift2;
    }

    public void setStartTimeShift2(String startTimeShift2) {
        this.startTimeShift2 = startTimeShift2;
    }

    public String getEndTimeShift2() {
        return endTimeShift2;
    }

    public void setEndTimeShift2(String endTimeShift2) {
        this.endTimeShift2 = endTimeShift2;
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

    public List<UserDutyShift> getUsers() {
        return users;
    }

    public void setUsers(List<UserDutyShift> users) {
        this.users = users;
    }
}