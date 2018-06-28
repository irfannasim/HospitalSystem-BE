package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.request.SaveTaxRequest;
import com.sd.his.utill.DateUtil;
import com.sd.his.utill.HISConstants;
import com.sd.his.utill.HISCoreUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 * @author    : Irfan Nasim
 * @Date      : 14-May-18
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
 * @FileName  : Tax
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "TAX")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tax implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RATE")
    private Double rate;

    @Column(name = "FROM_DATE")
    private Long fromDate;

    @Column(name = "TO_DATE")
    private Long toDate;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false)
    private boolean active;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @JsonIgnore
    @OneToMany(targetEntity = MedicalService.class, mappedBy = "tax", fetch = FetchType.LAZY)
    private List<MedicalService> medicalServices;

    public Tax() {
    }

    public Tax(SaveTaxRequest saveTaxRequest) {
        this.name = saveTaxRequest.getName();
        this.description = saveTaxRequest.getDescription();
        this.rate = saveTaxRequest.getRate();
        this.fromDate = DateUtil.getMillisFromStringDate(saveTaxRequest.getFromDate(), HISConstants.DATE_FORMATE_THREE);
        this.toDate = DateUtil.getMillisFromStringDate(saveTaxRequest.getToDate(), HISConstants.DATE_FORMATE_THREE);
        this.active = saveTaxRequest.isActive();
        this.deleted = false;
        this.createdOn = System.currentTimeMillis();
        this.updatedOn = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Tax{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getFromDate() {
        return fromDate;
    }

    public void setFromDate(Long fromDate) {
        this.fromDate = fromDate;
    }

    public Long getToDate() {
        return toDate;
    }

    public void setToDate(Long toDate) {
        this.toDate = toDate;
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

    public List<MedicalService> getMedicalServices() {
        return medicalServices;
    }

    public void setMedicalServices(List<MedicalService> medicalServices) {
        this.medicalServices = medicalServices;
    }
}
