package com.sd.his.wrapper;

import com.sd.his.model.Insurance;
import com.sd.his.model.Profile;
import com.sd.his.model.User;
import com.sd.his.request.PatientRequest;

import java.util.List;

public class PatientWrapper {

    long userId;
    String userType;
    String email;
    String userName;
    private long primaryDoctorId;
    private boolean active;
    private boolean deleted;
    private ProfileWrapper profileWrapper;
    private InsuranceWrapper insuranceWrapper;

    public PatientWrapper() {
    }

    public PatientWrapper(User user, Profile profile, Insurance insurance) {
        this.userId = user.getId();
        this.userName = user.getUsername();
        this.active = user.isActive();
        this.deleted = user.isDeleted();
        this.email = user.getEmail();
        this.userType = user.getUserType();
        if (user.getPrimaryDoctor() != null) {
            this.primaryDoctorId = user.getPrimaryDoctor().getId();
        }

        this.setProfileWrapper(new ProfileWrapper(profile));
        this.setInsuranceWrapper(new InsuranceWrapper(insurance));
    }

    public PatientWrapper(User patient) {
        this.userId = patient.getId();
        this.userName = patient.getUsername();
        this.email = patient.getEmail();
        this.profileWrapper.setFirstName(patient.getProfile().getFirstName());
        this.profileWrapper.setLastName(patient.getProfile().getLastName());
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getPrimaryDoctorId() {
        return primaryDoctorId;
    }

    public void setPrimaryDoctorId(long primaryDoctorId) {
        this.primaryDoctorId = primaryDoctorId;
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

    public ProfileWrapper getProfileWrapper() {
        return profileWrapper;
    }

    public void setProfileWrapper(ProfileWrapper profileWrapper) {
        this.profileWrapper = profileWrapper;
    }

    public InsuranceWrapper getInsuranceWrapper() {
        return insuranceWrapper;
    }

    public void setInsuranceWrapper(InsuranceWrapper insuranceWrapper) {
        this.insuranceWrapper = insuranceWrapper;
    }
}
