package com.sd.his.response;

import com.sd.his.model.User;

/*
 * @author    : Irfan Nasim
 * @Date      : 26-Jun-18
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
 * @Package   : com.sd.his.response
 * @FileName  : ProfileImageUploadResponse
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class ProfileImageUploadResponse {

    private long userId;
    private String firstName;
    private String lastName;
    private String profileImgUrl;

    public ProfileImageUploadResponse() {
    }

    public ProfileImageUploadResponse(User user) {
        this.userId = user.getId();
        this.firstName = user.getProfile().getFirstName();
        this.lastName = user.getProfile().getLastName();
        this.profileImgUrl = user.getProfile().getProfileImg();
    }

    public ProfileImageUploadResponse(long userId, String firstName, String lastName, String profileImgUrl) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImgUrl = profileImgUrl;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
}
