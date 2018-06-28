package com.sd.his.wrapper;

import com.sd.his.model.Insurance;
import com.sd.his.utill.DateUtil;
import com.sd.his.utill.HISConstants;

import java.util.Date;

public class InsuranceWrapper {

    private long insuranceId;
    private String company;
    private String insuranceID;
    private String groupNumber;
    private String planName;
    private String planType;
    private String cardIssuedDate;
    private String cardExpiryDate;
    private String primaryInsuranceNotes;
    private String photoFront;
    private String photoBack;


    public InsuranceWrapper() {
    }

    public InsuranceWrapper(Insurance insurance) {
        this.insuranceId = insurance.getId();
        this.company = insurance.getCompany();
        this.insuranceID = insurance.getInsuranceID();
        this.groupNumber = insurance.getGroupNumber();
        this.planName = insurance.getPlanName();
        this.planType = insurance.getPlanType();
        this.cardIssuedDate = insurance.getCardIssuedDate() == null ? "" : DateUtil.getFormattedDateFromDate(insurance.getCardIssuedDate(), HISConstants.DATE_FORMATE_THREE);
        this.cardExpiryDate = insurance.getCardExpiryDate() == null ? "" :DateUtil.getFormattedDateFromDate(insurance.getCardExpiryDate(), HISConstants.DATE_FORMATE_THREE);
        this.primaryInsuranceNotes = insurance.getPrimaryInsuranceNotes();
        this.photoFront = insurance.getPhotoFront();
        this.photoBack = insurance.getPhotoBack();
    }

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
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

    public String getCardIssuedDate() {
        return cardIssuedDate;
    }

    public void setCardIssuedDate(String cardIssuedDate) {
        this.cardIssuedDate = cardIssuedDate;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
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
}
