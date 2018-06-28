package com.sd.his.model;


import com.sd.his.enums.UserTypeEnum;
import com.sd.his.request.PatientRequest;
import com.sd.his.utill.DateUtil;
import com.sd.his.utill.HISConstants;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.utill.JSONUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROFILE")
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "FOREIGN_NAME")
    private String foreignName;

    @Column(name = "HOME_PHONE")
    private String homePhone;

    @Column(name = "CELL_PHONE")
    private String cellPhone;

    @Column(name = "DISABLE_SMS_TEXT", columnDefinition = "boolean default true")
    private Boolean disableSMSText;

    @Column(name = "OFFICE_PHONE")
    private String officePhone;

    @Column(name = "OFFICE_EXTENSION")
    private String officeExtension;

    @Column(name = "ACCOUNT_EXPIRY")
    private String accountExpiry;

    @Column(name = "PREFERRED_COMMUNICATION")
    private String preferredCommunication;

    @Column(name = "REMINDER_LANGUAGE")
    private String reminderLanguage;


    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean active = true;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted = false;

    @Column(name = "IS_SEND_BILLING_REPORT")
    private Boolean sendBillingReport = false;

    @Column(name = "IS_USE_RECEIPT_DASHBOARD", columnDefinition = "boolean default false")
    private Boolean useReceptDashBoard;

    @Column(name = "IS_OTHER_DOCTOR_DASHBOARD")
    private Boolean otherDoctorDashBoard = false;

    @Column(name = "IS_MANAGE_PATIENT_RECORDS")
    private Boolean managePatientRecords;

    @Column(name = "IS_MANAGE_PATIENT_INVOICES")
    private Boolean managePatientInvoices = false;

    @Column(name = "CHECK_UP_INTERVAL")
    private Long checkUpInterval;

/*   @ElementCollection
    @Column(name = "WORKING_DAYS")
    private Set<String> workingDays ;*/

    @ElementCollection
    @Column(name = "WORKING_DAYS")
    private List<String> workingDays = new ArrayList<>();

    @Column(name = "ALLOW_DISCOUNT")
    private Boolean allowDiscount = false;


    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PROFILE_IMG")
    private String profileImg;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STREET_ADDRESS")
    private String streetAddress;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "FORMATTED_ADDRESS")
    private String formattedAddress;

    @Column(name = "MARTIAL_STATUS")
    private String martialStatus;

    @Column(name = "EMERGENCY_CONTACT_NAME")
    private String emergencyContactName;

    @Column(name = "EMERGENCY_CONTACT_PHONE")
    private String emergencyContactPhone;

    @Column(name = "EMERGENCY_CONTACT_RELATION")
    private String emergencyContactRelation;

    @Column(name = "SIGNATURE_ON_FILE")
    private Boolean signatureOnFile;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PATIENT_SSN")
    private String patientSSN;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "OTHER_DOCTOR_DASHBOARD")
    private String otherDashboard;

    @Column(name = "CREATED_ON")
    private Long createdOn;

    @Column(name = "UPDATED_ON")
    private Long updatedOn;

    @Column(name = "ABOUT_ME")
    private String aboutMe;

    @Column(name = "TITLE_PREFIX")
    private String titlePrefix;

    @Column(name = "RACES")
    private String races;

    public Profile() {
    }

    public Profile(PatientRequest patientRequest) throws ParseException {
        this.id = patientRequest.getProfileId() > 0 ? patientRequest.getProfileId() : null;
        this.patientSSN = patientRequest.getPatientSSN();
        if (!HISCoreUtil.isNull(patientRequest.getDob())) {
            this.dob = DateUtil.getDateFromString(patientRequest.getDob(), HISConstants.DATE_FORMATE_THREE);
        }
        this.titlePrefix = patientRequest.getTitlePrefix();
        this.firstName = patientRequest.getFirstName();
        this.middleName = patientRequest.getMiddleName();
        this.lastName = patientRequest.getLastName();
        this.foreignName = patientRequest.getForeignName();
        this.homePhone = patientRequest.getHomePhone();
        this.cellPhone = patientRequest.getCellPhone();
        this.disableSMSText = patientRequest.isDisableSMSTxt();
        this.officePhone = patientRequest.getOfficePhone();
        this.officeExtension = patientRequest.getOfficeExtension();
        this.preferredCommunication = patientRequest.getPreferredCommunication();
        this.reminderLanguage = patientRequest.getReminderLanguage();

        this.accountExpiry = "";
        this.workingDays = new ArrayList<>();
        this.gender = patientRequest.getGender();
        this.country = patientRequest.getCountry();
        this.races = JSONUtil.gsonListToJSON(patientRequest.getRaces());
        this.streetAddress = patientRequest.getStreetAddress();
        this.zipCode = patientRequest.getZipCode();
        this.city = patientRequest.getCity();
        this.state = patientRequest.getState();
        this.formattedAddress = patientRequest.getFormattedAddress();
        this.martialStatus = patientRequest.getMartial();
        this.emergencyContactName = patientRequest.getEmergencyContactName();
        this.emergencyContactPhone = patientRequest.getEmergencyContactPhone();
        this.emergencyContactRelation = patientRequest.getEmergencyContactRelation();
        this.status = patientRequest.isProfileStatus() ? "ACTIVE" : "IN_ACTIVE";
        this.signatureOnFile = patientRequest.isSignatureOnFile();
        this.type = UserTypeEnum.PATIENT.toString();
    }

    public Profile(Profile profile, PatientRequest patientRequest) throws ParseException {
        profile.patientSSN = patientRequest.getPatientSSN();
        profile.titlePrefix = patientRequest.getTitlePrefix();
        profile.firstName = patientRequest.getFirstName();
        profile.middleName = patientRequest.getMiddleName();
        profile.lastName = patientRequest.getLastName();
        profile.foreignName = patientRequest.getForeignName();
        profile.homePhone = patientRequest.getHomePhone();
        profile.cellPhone = patientRequest.getCellPhone();
        profile.disableSMSText = patientRequest.isDisableSMSTxt();
        profile.officePhone = patientRequest.getOfficePhone();
        profile.officeExtension = patientRequest.getOfficeExtension();

        profile.preferredCommunication = patientRequest.getPreferredCommunication();
        profile.reminderLanguage = patientRequest.getReminderLanguage();

        profile.accountExpiry = "";
        profile.workingDays = new ArrayList<>();
        if (!HISCoreUtil.isNull(patientRequest.getDob()))
        profile.dob = DateUtil.getDateFromString(patientRequest.getDob(), HISConstants.DATE_FORMATE_THREE);
        profile.gender = patientRequest.getGender();
        profile.country = patientRequest.getCountry();
        profile.races = JSONUtil.gsonListToJSON(patientRequest.getRaces());
        profile.streetAddress = patientRequest.getStreetAddress();
        profile.zipCode = patientRequest.getZipCode();
        profile.city = patientRequest.getCity();
        profile.state = patientRequest.getState();
        profile.formattedAddress = patientRequest.getFormattedAddress();
        profile.martialStatus = patientRequest.getMartial();
        profile.emergencyContactName = patientRequest.getEmergencyContactName();
        profile.emergencyContactPhone = patientRequest.getEmergencyContactPhone();
        profile.emergencyContactRelation = patientRequest.getEmergencyContactRelation();
        profile.signatureOnFile = patientRequest.isSignatureOnFile();

        profile.type = UserTypeEnum.PATIENT.toString();
        profile.status = patientRequest.isProfileStatus() ? "ACTIVE" : "IN_ACTIVE";


    }

    public Long getId() {
        return id;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
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

    public Boolean getDisableSMSText() {
        return disableSMSText;
    }

    public void setDisableSMSText(Boolean disableSMSText) {
        this.disableSMSText = disableSMSText;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficeExtension() {
        return officeExtension;
    }

    public void setOfficeExtension(String officeExtension) {
        this.officeExtension = officeExtension;
    }

    public String getAccountExpiry() {
        return accountExpiry;
    }

    public void setAccountExpiry(String accountExpiry) {
        this.accountExpiry = accountExpiry;
    }

    public String getPreferredCommunication() {
        return preferredCommunication;
    }

    public void setPreferredCommunication(String preferredCommunication) {
        this.preferredCommunication = preferredCommunication;
    }

    public String getReminderLanguage() {
        return reminderLanguage;
    }

    public void setReminderLanguage(String reminderLanguage) {
        this.reminderLanguage = reminderLanguage;
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

    public Long getCheckUpInterval() {
        return checkUpInterval;
    }

    public void setCheckUpInterval(Long checkUpInterval) {
        this.checkUpInterval = checkUpInterval;
    }

    public List<String> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<String> workingDays) {
        this.workingDays = workingDays;
    }

    public Boolean getAllowDiscount() {
        return allowDiscount;
    }

    public void setAllowDiscount(Boolean allowDiscount) {
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getEmergencyContactRelation() {
        return emergencyContactRelation;
    }

    public void setEmergencyContactRelation(String emergencyContactRelation) {
        this.emergencyContactRelation = emergencyContactRelation;
    }

    public Boolean getSignatureOnFile() {
        return signatureOnFile;
    }

    public void setSignatureOnFile(Boolean signatureOnFile) {
        this.signatureOnFile = signatureOnFile;
    }

    public String getCountry() {
        return country;
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

    public String getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(String patientSSN) {
        this.patientSSN = patientSSN;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
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

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getTitlePrefix() {
        return titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    public String getRaces() {
        return races;
    }

    public void setRaces(String races) {
        this.races = races;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}