package com.sd.his.response;

import com.sd.his.model.Organization;
import com.sd.his.model.Profile;
import com.sd.his.model.Speciality;
import com.sd.his.model.User;

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
public class OrganizationResponseWrapper {

    Long id;
    String firstName;
    String lastName;
    String companyName;
    String password;
    String confirmPassword;
    String homePhone;
    String cellPhone;
    String officePhone;
    String timezone;
    String appointmentSerial;
    String website;
    String defaultBranch;
    Long durationOfExam;
    String followUpExam;
    String email;
    Speciality speciality;
    User user;

    public OrganizationResponseWrapper() {
    }

    public OrganizationResponseWrapper(Long id, String companyName, String cellPhone, String appointmentSerial, String website, String email) {
        this.id = id;
        this.companyName = companyName;
        this.cellPhone = cellPhone;
        this.appointmentSerial = appointmentSerial;
        this.website = website;
        this.email = email;
    }

    public OrganizationResponseWrapper(Organization organization) {
        this.cellPhone = organization.getCellPhone();
        this.homePhone = organization.getHomePhone();
        this.officePhone = organization.getOfficePhone();
        this.companyName = organization.getCompanyName();
        this.appointmentSerial = organization.getAptSerialStart();
        this.defaultBranch = organization.getDefaultBranch();
        this.durationOfExam = organization.getDurationOFExam();
        this.followUpExam = organization.getDurationFollowUp();
        this.timezone = organization.getTimezone();
        this.website = organization.getWebsite();
        this.user = organization.getUser();

    }

    public OrganizationResponseWrapper(User user) {
        this.firstName = user.getProfile().getFirstName();
        this.lastName = user.getProfile().getLastName();
        this.email = user.getEmail();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getAppointmentSerial() {
        return appointmentSerial;
    }

    public void setAppointmentSerial(String appointmentSerial) {
        this.appointmentSerial = appointmentSerial;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public Long getDurationOfExam() {
        return durationOfExam;
    }

    public void setDurationOfExam(Long durationOfExam) {
        this.durationOfExam = durationOfExam;
    }

    public String getFollowUpExam() {
        return followUpExam;
    }

    public void setFollowUpExam(String followUpExam) {
        this.followUpExam = followUpExam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}