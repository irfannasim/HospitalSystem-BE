package com.sd.his.request;

import com.sd.his.model.Tax;
import com.sd.his.utill.DateUtil;
import com.sd.his.utill.HISConstants;

/*
 * @author    : irfan
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
 * @Package   : com.sd.his.request
 * @FileName  : SaveTaxRequest
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class SaveTaxRequest {

    private long id;
    private String name;
    private String description;
    private double rate;
    private String fromDate;
    private String toDate;
    private boolean active;

    public SaveTaxRequest() {
    }


    public SaveTaxRequest(Tax tax) {
        this.id = tax.getId();
        this.name = tax.getName();
        this.description = tax.getDescription();
        this.rate = tax.getRate();
        this.fromDate = DateUtil.getDateFromMillis(tax.getFromDate(), HISConstants.DATE_FORMATE_THREE);
        this.toDate = DateUtil.getDateFromMillis(tax.getToDate(), HISConstants.DATE_FORMATE_THREE);
        this.active = tax.isActive();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
