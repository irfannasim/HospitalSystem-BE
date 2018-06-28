package com.sd.his.wrapper;

import com.sd.his.model.Profile;
import com.sd.his.utill.DateUtil;
import com.sd.his.utill.HISConstants;

import java.util.ArrayList;
import java.util.List;

public class ProfileWrapper {

    private long profileId;
    private String patientSSN;
    private String firstName;
    private String lastName;
    private String homePhone;
    private String cellPhone;
    private String accountExpiry;
    private boolean active;
    private boolean deleted;
    private boolean sendBillingReport;
    private boolean useReceptDashBoard;
    private boolean otherDoctorDashBoard;
    private boolean managePatientRecords;
    private boolean managePatientInvoices;
    private long checkUpInterval;
    private List<String> workingDays = new ArrayList<>();
    private boolean allowDiscount;
    private String gender;
    private String profileImg;
    private String address;
    private String city;
    private String state;
    private String country;
    private String races;
    private String status;
    private String dob;
    private String type;
    private String otherDashboard;
    private long createdOn;
    private long updatedOn;
    private String aboutMe;


    public ProfileWrapper() {
    }

    public ProfileWrapper(Profile profile) {
        this.profileId = profile.getId();
        this.patientSSN = profile.getPatientSSN();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.homePhone = profile.getHomePhone();
        this.cellPhone = profile.getCellPhone();
        this.accountExpiry = profile.getAccountExpiry() == null ? "" : profile.getAccountExpiry();
        this.active = profile.getActive() == null ? false : profile.getActive();
        this.deleted = profile.getDeleted() == null ? false : profile.getDeleted();
        this.sendBillingReport = profile.getSendBillingReport() == null ? false : profile.getSendBillingReport();
        this.useReceptDashBoard = profile.getUseReceptDashBoard() == null ? false : profile.getUseReceptDashBoard();
        this.otherDoctorDashBoard = profile.getOtherDoctorDashBoard() == null ? false : profile.getOtherDoctorDashBoard();
        this.managePatientRecords = profile.getManagePatientRecords() == null ? false : profile.getManagePatientRecords();
        this.managePatientInvoices = profile.getManagePatientInvoices() == null ? false : profile.getManagePatientInvoices();
        this.workingDays = profile.getWorkingDays() == null ? new ArrayList<>() : profile.getWorkingDays();
        this.allowDiscount = profile.getAllowDiscount() == null ? false : profile.getAllowDiscount();
        this.gender = profile.getGender() == null ? null : profile.getGender();
        this.profileImg = profile.getProfileImg() == null ? "" : profile.getProfileImg();
        this.address = profile.getAddress() == null ? "" : profile.getAddress();
        this.city = profile.getCity() == null ? "" : profile.getCity();
        this.state = profile.getState() == null ? "" : profile.getState();
        this.country = profile.getCountry() == null ? "" : profile.getCountry();
        this.races = profile.getRaces();
        this.status = profile.getStatus() == null ? "Active" : profile.getStatus();
        this.dob = profile.getDob() == null ? "" : DateUtil.getFormattedDateFromDate(profile.getDob(), HISConstants.DATE_FORMATE_THREE);
        this.type = profile.getType() == null ? "" : profile.getType();
        this.otherDashboard = profile.getOtherDashboard();
        this.createdOn = profile.getCreatedOn() == null ? 0 : profile.getCreatedOn();
        this.updatedOn = profile.getUpdatedOn() == null ? 0 : profile.getUpdatedOn();
        this.aboutMe = profile.getAboutMe();
        this.deleted = profile.getDeleted();
    }

    public long getProfileId() {
        return profileId;
    }

    public String getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(String patientSSN) {
        this.patientSSN = patientSSN;
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

    public boolean isSendBillingReport() {
        return sendBillingReport;
    }

    public void setSendBillingReport(boolean sendBillingReport) {
        this.sendBillingReport = sendBillingReport;
    }

    public boolean isUseReceptDashBoard() {
        return useReceptDashBoard;
    }

    public void setUseReceptDashBoard(boolean useReceptDashBoard) {
        this.useReceptDashBoard = useReceptDashBoard;
    }

    public boolean isOtherDoctorDashBoard() {
        return otherDoctorDashBoard;
    }

    public void setOtherDoctorDashBoard(boolean otherDoctorDashBoard) {
        this.otherDoctorDashBoard = otherDoctorDashBoard;
    }

    public boolean isManagePatientRecords() {
        return managePatientRecords;
    }

    public void setManagePatientRecords(boolean managePatientRecords) {
        this.managePatientRecords = managePatientRecords;
    }

    public boolean isManagePatientInvoices() {
        return managePatientInvoices;
    }

    public void setManagePatientInvoices(boolean managePatientInvoices) {
        this.managePatientInvoices = managePatientInvoices;
    }

    public boolean isAllowDiscount() {
        return allowDiscount;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
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

    public String getAccountExpiry() {
        return accountExpiry;
    }

    public void setAccountExpiry(String accountExpiry) {
        this.accountExpiry = accountExpiry;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getSendBillingReport() {
        return sendBillingReport;
    }

    public void setSendBillingReport(Boolean sendBillingReport) {
        this.sendBillingReport = sendBillingReport;
    }

    public Boolean getUseReceptDashBoard() {
        return useReceptDashBoard;
    }

    public void setUseReceptDashBoard(Boolean useReceptDashBoard) {
        this.useReceptDashBoard = useReceptDashBoard;
    }

    public Boolean getOtherDoctorDashBoard() {
        return otherDoctorDashBoard;
    }

    public void setOtherDoctorDashBoard(Boolean otherDoctorDashBoard) {
        this.otherDoctorDashBoard = otherDoctorDashBoard;
    }

    public Boolean getManagePatientRecords() {
        return managePatientRecords;
    }

    public void setManagePatientRecords(Boolean managePatientRecords) {
        this.managePatientRecords = managePatientRecords;
    }

    public Boolean getManagePatientInvoices() {
        return managePatientInvoices;
    }

    public void setManagePatientInvoices(Boolean managePatientInvoices) {
        this.managePatientInvoices = managePatientInvoices;
    }

    public long getCheckUpInterval() {
        return checkUpInterval;
    }

    public void setCheckUpInterval(long checkUpInterval) {
        this.checkUpInterval = checkUpInterval;
    }

    public List<String> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<String> workingDays) {
        this.workingDays = workingDays;
    }

    public boolean getAllowDiscount() {
        return allowDiscount;
    }

    public void setAllowDiscount(boolean allowDiscount) {
        this.allowDiscount = allowDiscount;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public String getRaces() {
        return races;
    }

    public void setRaces(String races) {
        this.races = races;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOtherDashboard() {
        return otherDashboard;
    }

    public void setOtherDashboard(String otherDashboard) {
        this.otherDashboard = otherDashboard;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
