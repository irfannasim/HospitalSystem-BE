package com.sd.his.wrapper;

import com.sd.his.model.User;

import java.util.List;

public class UserWrapper {
    long id;
    String userType;
    String email;
    String userName;
    String password;
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
    String aboutMe;
    List<RoleWrapper> roles;
    List<PermissionWrapper> permissions;
    List<BranchWrapper> branches;
    String commaSeparatedRoles;


    public UserWrapper() {
    }

    public UserWrapper(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
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
        this.aboutMe = user.getProfile().getAboutMe();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<BranchWrapper> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchWrapper> branches) {
        this.branches = branches;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<PermissionWrapper> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionWrapper> permissions) {
        this.permissions = permissions;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public List<RoleWrapper> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleWrapper> roles) {
        this.roles = roles;
    }

    public String getCommaSeparatedRoles() {
        return commaSeparatedRoles;
    }

    public void setCommaSeparatedRoles(String commaSeparatedRoles) {
        this.commaSeparatedRoles = commaSeparatedRoles;
    }
}
