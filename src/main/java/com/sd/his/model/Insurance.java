package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.request.PatientRequest;
import com.sd.his.utill.DateUtil;
import com.sd.his.utill.HISConstants;
import com.sd.his.utill.HISCoreUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.text.ParseException;
import java.util.Date;

/*
 * @author    : Irfan Nasim
 * @Date      : 04-Jun-18
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
 * @FileName  : Insurance
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "INSURANCE")
public class Insurance {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "INSURANCE_ID")
    private String insuranceID;

    @Column(name = "GROUP_NUMBER")
    private String groupNumber;

    @Column(name = "PLAN_NAME")
    private String planName;

    @Column(name = "PLAN_TYPE")
    private String planType;

    @Column(name = "CARD_ISSUED_DATE")
    private Date cardIssuedDate;

    @Column(name = "CARD_EXPIRY_DATE")
    private Date cardExpiryDate;

    @Column(name = "PRIMARY_INSURANCE_NOTES")
    private String primaryInsuranceNotes;

    @Column(name = "PHOTO_FRONT")
    private String photoFront;

    @Column(name = "PHOTO_BACK")
    private String photoBack;

    @Column(name = "CREATED_ON")
    private Long created;

    @Column(name = "UPDATED_ON")
    private Long updated;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false")
    private Boolean deleted;

    public Insurance() {
    }

    public Insurance(PatientRequest patientRequest) throws ParseException {
        this.id = patientRequest.getInsuranceId() > 0 ? patientRequest.getInsuranceId() : null;
        this.company = patientRequest.getCompany();
        this.insuranceID = patientRequest.getInsuranceIdNumber();
        this.groupNumber = patientRequest.getGroupNumber();
        this.planName = patientRequest.getPlanName();
        this.planType = patientRequest.getPlanType();
        if (!HISCoreUtil.isNull(patientRequest.getCardIssuedDate())) {
            this.cardIssuedDate = DateUtil.getDateFromString(patientRequest.getCardIssuedDate(), HISConstants.DATE_FORMATE_THREE);
        }
        if (!HISCoreUtil.isNull(patientRequest.getCardExpiryDate())) {
            this.cardExpiryDate = DateUtil.getDateFromString(patientRequest.getCardExpiryDate(), HISConstants.DATE_FORMATE_THREE);
        }
        this.primaryInsuranceNotes = patientRequest.getPrimaryInsuranceNotes();
        //this.photoFront = patientRequest.getPhotoFront();
        //this.photoBack = patientRequest.getPhotoBack();
        if (patientRequest.getInsuranceId() <= 0) {
            this.created = System.currentTimeMillis();
        }
        this.updated = System.currentTimeMillis();
        this.deleted = false;

    }

    public Insurance(Insurance insurance, PatientRequest patientRequest) throws ParseException {
        insurance.company = patientRequest.getCompany();
        insurance.insuranceID = patientRequest.getInsuranceIdNumber();
        insurance.groupNumber = patientRequest.getGroupNumber();
        insurance.planName = patientRequest.getPlanName();
        insurance.planType = patientRequest.getPlanType();
        if (!HISCoreUtil.isNull(patientRequest.getCardIssuedDate()))
            insurance.cardIssuedDate = DateUtil.getDateFromString(patientRequest.getCardIssuedDate() + "", HISConstants.DATE_FORMATE_THREE);
        if (!HISCoreUtil.isNull(patientRequest.getCardExpiryDate()))
            insurance.cardExpiryDate = DateUtil.getDateFromString(patientRequest.getCardExpiryDate() + "", HISConstants.DATE_FORMATE_THREE);
        insurance.primaryInsuranceNotes = patientRequest.getPrimaryInsuranceNotes();
        //this.photoFront = patientRequest.getPhotoFront();
        //this.photoBack = patientRequest.getPhotoBack();
        insurance.created = System.currentTimeMillis();
        insurance.updated = System.currentTimeMillis();
        insurance.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public Date getCardIssuedDate() {
        return cardIssuedDate;
    }

    public void setCardIssuedDate(Date cardIssuedDate) {
        this.cardIssuedDate = cardIssuedDate;
    }

    public Date getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(Date cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getPrimaryInsuranceNotes() {
        return primaryInsuranceNotes;
    }

    public void setPrimaryInsuranceNotes(String primaryInsuranceNotes) {
        this.primaryInsuranceNotes = primaryInsuranceNotes;
    }

    public String getPhotoFront() {
        return photoFront;
    }

    public void setPhotoFront(String photoFront) {
        this.photoFront = photoFront;
    }

    public String getPhotoBack() {
        return photoBack;
    }

    public void setPhotoBack(String photoBack) {
        this.photoBack = photoBack;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
