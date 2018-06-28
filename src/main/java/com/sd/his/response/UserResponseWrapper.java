package com.sd.his.response;

import com.sd.his.model.*;
import com.sd.his.wrapper.BranchWrapper;

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
 * @Package   : com.sd.his.*
 * @FileName  : UserAuthAPI
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class UserResponseWrapper {

    long id;
    String userType;
    String email;
    String userName;
    String firstName;
    String lastName;
    String phoneNumber;
    String address;
    String state;
    String city;
    String country;
    String gender;
    String profileImg;
    String status;
    BranchWrapper branch;
    DutyShift dutyShift;
    com.sd.his.model.Profile profile;
    Vacation vacation;
    String workingDaysOfDoctor;
    List<ClinicalDepartment> clinicalDepartments;
    List<Branch> VisitBranches;
    List<DutyWithDoctor> dutyWithDoctors;

    public UserResponseWrapper() {
    }

    public UserResponseWrapper(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.userType = user.getUserType();
        this.userName = user.getUsername();
        this.firstName = user.getProfile().getFirstName();
        this.lastName = user.getProfile().getLastName();
        this.phoneNumber = user.getProfile().getCellPhone();
        this.address = user.getProfile().getAddress();
        this.state = user.getProfile().getState();
        this.city = user.getProfile().getCity();
        this.userType = user.getUserType();
        this.country = user.getProfile().getCountry();
        this.gender = user.getProfile().getGender();
        this.profileImg = user.getProfile().getProfileImg();
        this.status = user.getProfile().getStatus();

    }

    public List<DutyWithDoctor> getDutyWithDoctors() {
        return dutyWithDoctors;
    }

    public void setDutyWithDoctors(List<DutyWithDoctor> dutyWithDoctors) {
        this.dutyWithDoctors = dutyWithDoctors;
    }

    public List<Branch> getVisitBranches() {
        return VisitBranches;
    }

    public void setVisitBranches(List<Branch> visitBranches) {
        VisitBranches = visitBranches;
    }

    public List<ClinicalDepartment> getClinicalDepartments() {
        return clinicalDepartments;
    }

    public void setClinicalDepartments(List<ClinicalDepartment> clinicalDepartments) {
        this.clinicalDepartments = clinicalDepartments;
    }

    public String getWorkingDaysOfDoctor() {
        return workingDaysOfDoctor;
    }

    public void setWorkingDaysOfDoctor(String workingDaysOfDoctor) {
        this.workingDaysOfDoctor = workingDaysOfDoctor;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public com.sd.his.model.Profile getProfile() {
        return profile;
    }

    public void setProfile(com.sd.his.model.Profile profile) {
        this.profile = profile;
    }

    public DutyShift getDutyShift() {
        return dutyShift;
    }

    public void setDutyShift(DutyShift dutyShift) {
        this.dutyShift = dutyShift;
    }

    public BranchWrapper getBranch() {
        return branch;
    }

    public void setBranch(BranchWrapper branch) {
        this.branch = branch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}