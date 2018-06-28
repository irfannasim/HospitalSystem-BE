package com.sd.his.request;

import com.sd.his.model.Insurance;
import com.sd.his.model.Profile;
import com.sd.his.model.User;
import com.sd.his.utill.DateUtil;
import com.sd.his.utill.HISConstants;
import com.sd.his.wrapper.RaceWrapper;

import java.io.File;
import java.util.List;

/**
 * Created by jamal on 6/7/2018.
 */
public class PatientRequest {
    //////IMPORTANT INFORMATION

    private long userId;
    private long selectedDoctor = -1;
    private String titlePrefix = "-1";
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String foreignName = "";
    private File patientPhoto;
    private String homePhone = "";
    private String cellPhone = "";
    private boolean disableSMSTxt = true;
    private String officePhone = "";
    private String officeExtension = "";
    private String email = "";
    private String userName = "";
    private String preferredCommunication = "ENGLISH";
    private String reminderLanguage = "ENGLISH";
    private boolean statusUser = false;
    /////// DEMOGRAPHY
    private long profileId;
    private String patientSSN = "";
    private String dob = "";
    private String gender = "MALE";
    List<RaceWrapper> races;
    private String racesString;
    private String country = "SAUDI ARAB";
    private String streetAddress = "";
    private String zipCode = "";
    private String city = "";
    private String state = "SAUDI ARAB";
    private String formattedAddress = "";
    private String martial = "SINGLE";
    private String emergencyContactName = "";
    private String emergencyContactPhone = "";
    private String emergencyContactRelation = "";
    private boolean signatureOnFile = false;
    private boolean profileStatus = true;

    ///////////////// INSURANCE
    private long insuranceId;
    private String company = "";
    private String insuranceIdNumber = "";
    private String groupNumber = "";
    private String planName = "";
    private String planType = "";
    private String cardIssuedDate = "";
    private String cardExpiryDate = "";
    private String primaryInsuranceNotes = "";
    private File photoFront;
    private File photoBack;

    public PatientRequest() {
    }

    public PatientRequest(User user, Profile profile, Insurance insurance) {
        this.userId = user.getId();
        this.userName = user.getUsername();
        this.statusUser = user.isActive();
//        this.deleted = user.isDeleted();
        this.email = user.getEmail();
//        this.Type = user.getUserType();

        this.profileId = profile.getId();
        this.patientSSN = profile.getPatientSSN();
        this.firstName = profile.getFirstName();
        this.middleName = profile.getMiddleName();
        this.lastName = profile.getLastName() == null ? "" : profile.getLastName();
        this.foreignName = profile.getForeignName();
        this.homePhone = profile.getHomePhone() == null ? "" : profile.getHomePhone();
        this.cellPhone = profile.getCellPhone() == null ? "" : profile.getCellPhone();
        this.officePhone = profile.getOfficePhone();
        this.officeExtension = profile.getOfficeExtension();
        this.preferredCommunication = profile.getPreferredCommunication();
        this.reminderLanguage = profile.getReminderLanguage();
        this.emergencyContactName = profile.getEmergencyContactName();
        this.emergencyContactPhone = profile.getEmergencyContactPhone();
        this.emergencyContactRelation = profile.getEmergencyContactRelation();
        this.disableSMSTxt = profile.getDisableSMSText();
        this.formattedAddress = profile.getFormattedAddress();
        this.streetAddress = profile.getStreetAddress();
        this.martial = profile.getMartialStatus();
        this.zipCode = profile.getZipCode();
        this.signatureOnFile = profile.getSignatureOnFile();

        this.gender = profile.getGender() == null ? null : profile.getGender();
//        this.profileImg = profile.getProfileImg() == null ? "" : profile.getProfileImg();
        this.city = profile.getCity() == null ? "" : profile.getCity();
        this.state = profile.getState() == null ? "" : profile.getState();
        this.country = profile.getCountry() == null ? "" : profile.getCountry();
        this.racesString = profile.getRaces();
        this.dob = profile.getDob() == null ? "" : DateUtil.getFormattedDateFromDate(profile.getDob(), HISConstants.DATE_FORMATE_THREE);

        this.titlePrefix = profile.getTitlePrefix();
        if (user.getPrimaryDoctor() != null) {
            this.selectedDoctor = user.getPrimaryDoctor().getId();
        }

        this.insuranceId = insurance.getId();
        this.company = insurance.getCompany();
        this.insuranceIdNumber = insurance.getInsuranceID();
        this.groupNumber = insurance.getGroupNumber();
        this.planName = insurance.getPlanName();
        this.planType = insurance.getPlanType();
        this.cardIssuedDate = insurance.getCardIssuedDate() == null ? "" : DateUtil.getFormattedDateFromDate(insurance.getCardIssuedDate(), HISConstants.DATE_FORMATE_THREE);
        this.cardExpiryDate = insurance.getCardExpiryDate() == null ? "" : DateUtil.getFormattedDateFromDate(insurance.getCardExpiryDate(), HISConstants.DATE_FORMATE_THREE);
        this.primaryInsuranceNotes = insurance.getPrimaryInsuranceNotes();
        /*this.photoFront = insurance.getPhotoFront();
        this.photoBack = insurance.getPhotoBack();*/
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public long getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(long selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public String getTitlePrefix() {
        return titlePrefix;
    }

    public void setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public File getPatientPhoto() {
        return patientPhoto;
    }

    public void setPatientPhoto(File patientPhoto) {
        this.patientPhoto = patientPhoto;
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

    public boolean isDisableSMSTxt() {
        return disableSMSTxt;
    }

    public void setDisableSMSTxt(boolean disableSMSTxt) {
        this.disableSMSTxt = disableSMSTxt;
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

    public boolean isStatusUser() {
        return statusUser;
    }

    public void setStatusUser(boolean statusUser) {
        this.statusUser = statusUser;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(String patientSSN) {
        this.patientSSN = patientSSN;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<RaceWrapper> getRaces() {
        return races;
    }

    public void setRaces(List<RaceWrapper> races) {
        this.races = races;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getMartial() {
        return martial;
    }

    public void setMartial(String martial) {
        this.martial = martial;
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

    public boolean isSignatureOnFile() {
        return signatureOnFile;
    }

    public void setSignatureOnFile(boolean signatureOnFile) {
        this.signatureOnFile = signatureOnFile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInsuranceIdNumber() {
        return insuranceIdNumber;
    }

    public void setInsuranceIdNumber(String insuranceIdNumber) {
        this.insuranceIdNumber = insuranceIdNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getCardIssuedDate() {
        return cardIssuedDate;
    }

    public void setCardIssuedDate(String cardIssuedDate) {
        this.cardIssuedDate = cardIssuedDate;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getPrimaryInsuranceNotes() {
        return primaryInsuranceNotes;
    }

    public void setPrimaryInsuranceNotes(String primaryInsuranceNotes) {
        this.primaryInsuranceNotes = primaryInsuranceNotes;
    }

    public File getPhotoFront() {
        return photoFront;
    }

    public void setPhotoFront(File photoFront) {
        this.photoFront = photoFront;
    }

    public File getPhotoBack() {
        return photoBack;
    }

    public void setPhotoBack(File photoBack) {
        this.photoBack = photoBack;
    }

    public boolean isProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(boolean profileStatus) {
        this.profileStatus = profileStatus;
    }

    public String getRacesString() {
        return racesString;
    }

    public void setRacesString(String racesString) {
        this.racesString = racesString;
    }
}
