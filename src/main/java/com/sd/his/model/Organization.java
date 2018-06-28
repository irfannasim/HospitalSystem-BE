package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
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
 * @FileName  : Organization
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "ORGANIZATION")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADMIN_ID")
    private User user;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "DEFAULT_BRANCH")
    private String defaultBranch;

    @Column(name = "DURATION_OF_EXAM")
    private Long durationOFExam;

    @Column(name = "TIMEZONE")
    private String timezone;

    @Column(name = "DURATION_FOLLOW_UP")
    private String durationFollowUp;

    @Column(name = "OFFICE_PHONE")
    private String officePhone;

    @Column(name = "HOME_PHONE")
    private String homePhone;

    @Column(name = "CELL_PHONE")
    private String cellPhone;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "APT_SERIAL_START")
    private String aptSerialStart;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default false", nullable = false)
    private boolean active;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @ManyToOne
    @JoinColumn(name = "ORG_ID")
    private Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "S3_BUCKET_ID")
    private S3Bucket s3Bucket;

    @JsonIgnore
    @OneToMany(targetEntity = Prefix.class, mappedBy = "prefix", fetch = FetchType.LAZY)
    private List<Prefix> prefixes;

    @JsonIgnore
    @OneToMany(targetEntity = OrganizationSpecialty.class, mappedBy = "organization", fetch = FetchType.LAZY)
    private List<OrganizationSpecialty> organization_specialties;


    public Organization() {
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public Long getDurationOFExam() {
        return durationOFExam;
    }

    public void setDurationOFExam(Long durationOFExam) {
        this.durationOFExam = durationOFExam;
    }

    public String getDurationFollowUp() {
        return durationFollowUp;
    }

    public void setDurationFollowUp(String durationFollowUp) {
        this.durationFollowUp = durationFollowUp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
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

    public String getAptSerialStart() {
        return aptSerialStart;
    }

    public void setAptSerialStart(String aptSerialStart) {
        this.aptSerialStart = aptSerialStart;
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

    public S3Bucket getS3Bucket() {
        return s3Bucket;
    }

    public void setS3Bucket(S3Bucket s3Bucket) {
        this.s3Bucket = s3Bucket;
    }

    public List<Prefix> getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(List<Prefix> prefixes) {
        this.prefixes = prefixes;
    }

    public List<OrganizationSpecialty> getOrganization_specialties() {
        return organization_specialties;
    }

    public void setOrganization_specialties(List<OrganizationSpecialty> organization_specialties) {
        this.organization_specialties = organization_specialties;
    }
}
