package com.sd.his.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.model.ICDVersion;

public class ICDVersionWrapper {

    private long id;
    private String name;
    private String title;
    private boolean status;
    private long updatedOn;
    private long createdOn;
    private boolean deleted;

    public ICDVersionWrapper() {
    }

    public ICDVersionWrapper(ICDVersion iCDVersion) {
        this.id = iCDVersion.getId();
        this.name = iCDVersion.getName();
        this.title = iCDVersion.getTitle();
        this.status = iCDVersion.isStatus();
        this.updatedOn = iCDVersion.getUpdatedOn();
        this.createdOn = iCDVersion.getCreatedOn();
        this.deleted = iCDVersion.isDeleted();
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
