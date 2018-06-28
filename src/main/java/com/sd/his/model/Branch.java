package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.wrapper.ExamRooms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
 * @FileName  : Branch
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "BRANCH")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NO_OF_ROOMS")
    private Integer noOfRooms;

    @Column(name = "BILLING_NAME")
    private String billingName;

    @Column(name = "BILLING_BRANCH_NAME")
    private String billingBranchName;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "OFFICE_PHONE")
    private String officePhone;

    @Column(name = "FORMATTED_ADDRESS")
    private String formattedAddress;

    @Column(name = "STATE")
    private String state;

    @Column(name = "OFFICE_START_TIME")
    private String officeStartTime;

    @Column(name = "OFFICE_END_TIME")
    private String officeEndTime;

    @Column(name = "BILLING_TAX_ID")
    private String billingTaxId;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false)
    private boolean active;

    @Column(name = "SYSTEM_BRANCH", columnDefinition = "boolean default false", nullable = false)
    private boolean systemBranch;

    @Column(name = "ZIP_CODE")
    private Integer zipCode;

    @Column(name = "ALLOW_ONLINE_SCHEDULE", columnDefinition = "boolean default true")
    private boolean allowOnlineSchedule;

    @Column(name = "SHOW_BRANCH_INFO_ONLINE", columnDefinition = "boolean default true")
    private boolean showBranchInfoOnline;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @JsonIgnore
    @OneToMany(targetEntity = BranchUser.class, mappedBy = "branch", fetch = FetchType.LAZY)
    private List<BranchUser> users;

    @JsonIgnore
    @OneToMany(targetEntity = UserVisitBranches.class, mappedBy = "branch", fetch = FetchType.LAZY)
    private List<UserVisitBranches> visitBranches;

    @JsonIgnore
    @OneToMany(targetEntity = Room.class, mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Room> rooms;

    @JsonIgnore
    @OneToMany(targetEntity = BranchClinicalDepartment.class, mappedBy = "branch", fetch = FetchType.LAZY)
    private List<BranchClinicalDepartment> clinicalDepartments;

    @JsonIgnore
    @OneToMany(targetEntity = BranchMedicalService.class, mappedBy = "branch", fetch = FetchType.LAZY)
    private List<BranchMedicalService> medicalServices;

    @JsonIgnore
    @OneToMany(targetEntity = Appointment.class, mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Branch() {
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", noOfRooms=" + noOfRooms +
                ", billingName='" + billingName + '\'' +
                ", billingBranchName='" + billingBranchName + '\'' +
                ", billingTaxId='" + billingTaxId + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
    }

    public boolean isSystemBranch() {
        return systemBranch;
    }

    public void setSystemBranch(boolean systemBranch) {
        this.systemBranch = systemBranch;
    }

    public List<UserVisitBranches> getVisitBranches() {
        return visitBranches;
    }

    public void setVisitBranches(List<UserVisitBranches> visitBranches) {
        this.visitBranches = visitBranches;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean isAllowOnlineSchedule() {
        return allowOnlineSchedule;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOfficeStartTime() {
        return officeStartTime;
    }

    public void setOfficeStartTime(String officeStartTime) {
        this.officeStartTime = officeStartTime;
    }

    public String getOfficeEndTime() {
        return officeEndTime;
    }

    public void setOfficeEndTime(String officeEndTime) {
        this.officeEndTime = officeEndTime;
    }

    public void setAllowOnlineSchedule(boolean allowOnlineSchedule) {
        this.allowOnlineSchedule = allowOnlineSchedule;
    }

    public boolean isShowBranchInfoOnline() {
        return showBranchInfoOnline;
    }

    public void setShowBranchInfoOnline(boolean showBranchInfoOnline) {
        this.showBranchInfoOnline = showBranchInfoOnline;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingBranchName() {
        return billingBranchName;
    }

    public void setBillingBranchName(String billingBranchName) {
        this.billingBranchName = billingBranchName;
    }

    public String getBillingTaxId() {
        return billingTaxId;
    }

    public void setBillingTaxId(String billingTaxId) {
        this.billingTaxId = billingTaxId;
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

    public List<BranchUser> getUsers() {
        return users;
    }

    public void setUsers(List<BranchUser> users) {
        this.users = users;
    }

    public List<BranchClinicalDepartment> getClinicalDepartments() {
        return clinicalDepartments;
    }

    public void setClinicalDepartments(List<BranchClinicalDepartment> clinicalDepartments) {
        this.clinicalDepartments = clinicalDepartments;
    }

    public List<BranchMedicalService> getMedicalServices() {
        return medicalServices;
    }

    public void setMedicalServices(List<BranchMedicalService> medicalServices) {
        this.medicalServices = medicalServices;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
