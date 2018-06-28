package com.sd.his.wrapper;

/*
 * @author    : Irfan Nasim
 * @Date      : 15-May-18
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
 * @Package   : com.sd.his.wrapper
 * @FileName  : MedicalServiceWrapper
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class MedicalServiceWrapper {

    private long id;
    private String title;
    private double fee;
    private String cost;
    private boolean status;
    private long branchId;
    private String branchName;
    private long dptId;
    private String dptName;
    private long taxId;
    private double taxRate;
    private String description;
    private long duration;

    public MedicalServiceWrapper() {
    }

    public MedicalServiceWrapper(long id, String title, double fee, String cost, boolean status, long branchId,
                                 String branchName, long dptId, String dptName, long taxId, double taxRate, String description, long duration) {
        this.id = id;
        this.title = title;
        this.fee = fee;
        this.cost = cost;
        this.status = status;
        this.branchId = branchId;
        this.branchName = branchName;
        this.dptId = dptId;
        this.dptName = dptName;
        this.taxId = taxId;
        this.taxRate = taxRate;
        this.description = description;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public long getDptId() {
        return dptId;
    }

    public void setDptId(long dptId) {
        this.dptId = dptId;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
