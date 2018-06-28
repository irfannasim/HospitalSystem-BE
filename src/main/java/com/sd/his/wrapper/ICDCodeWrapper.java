package com.sd.his.wrapper;

import com.sd.his.model.ICDCode;


public class ICDCodeWrapper {


    private long id;
    private String code;
    private String title;
    private boolean status;
    private boolean deleted;
    private long updatedOn;
    private long createdOn;
    private boolean checkedCode;
    private String description;
    private String descriptionCodeVersion;
    private ICDVersionWrapper iCDVersion;

    public ICDCodeWrapper() {
    }

    public ICDCodeWrapper(ICDCode icd) {
        this.id = icd.getId();
        this.code = icd.getCode();
        this.title = icd.getTitle();
        this.status = icd.isStatus();
        this.deleted = icd.isDeleted();
        this.createdOn = icd.getCreatedOn();
        this.updatedOn = icd.getUpdatedOn();
        this.description = icd.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public ICDVersionWrapper getICDVersionWrapper() {
        return iCDVersion;
    }

    public void setICDVersionWrapper(ICDVersionWrapper iCDVersionWrapper) {
        this.iCDVersion = iCDVersionWrapper;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCheckedCode() {
        return checkedCode;
    }

    public void setCheckedCode(boolean checkedCode) {
        this.checkedCode = checkedCode;
    }

    public String getDescriptionCodeVersion() {
        return descriptionCodeVersion;
    }

    public void setDescriptionCodeVersion(String descriptionCodeVersion) {
        this.descriptionCodeVersion = descriptionCodeVersion;
    }
}
