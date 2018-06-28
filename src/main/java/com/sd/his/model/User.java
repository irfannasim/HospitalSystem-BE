package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sd.his.enums.UserTypeEnum;
import com.sd.his.request.PatientRequest;
import org.hibernate.hql.spi.id.TableBasedDeleteHandlerImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
 * @FileName  : User
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "USER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String username;

    @NotNull
    @Column(name = "USER_TYPE")
    private String userType;

    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid Email")
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default false", nullable = false)
    private boolean active;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @Column(name = "SYSTEM_DOCTOR", columnDefinition = "boolean default false")
    private boolean systemDoctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSURANCE_ID")
    private Insurance insurance;

    @JsonIgnore
    @OneToMany(targetEntity = UserPermission.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPermission> permissions;

    @JsonIgnore
    @OneToMany(targetEntity = UserRole.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRole> roles;

    @JsonIgnore
    @OneToMany(targetEntity = BranchUser.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<BranchUser> branches;

    @JsonIgnore
    @OneToMany(targetEntity = Vacation.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Vacation> vacations;

    @JsonIgnore
    @OneToMany(targetEntity = DepartmentUser.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<DepartmentUser> departments;

    @JsonIgnore
    @OneToMany(targetEntity = DutyWithDoctor.class, mappedBy = "nurse", fetch = FetchType.LAZY)
    private List<DutyWithDoctor> doctor;

    @JsonIgnore
    @OneToMany(targetEntity = DutyWithDoctor.class, mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<DutyWithDoctor> nurse;

    @JsonIgnore
    @OneToMany(targetEntity = UserVisitBranches.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserVisitBranches> userVisitBranches;

    @JsonIgnore
    @OneToMany(targetEntity = UserMedicalService.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserMedicalService> medicalServices;

    @JsonIgnore
    @OneToMany(targetEntity = UserDutyShift.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserDutyShift> dutyShifts;

    @JsonIgnore
    @OneToMany(targetEntity = Appointment.class, mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRIMARY_DOCTOR")
    private User primaryDoctor;

    public User() {
    }

    public User(PatientRequest patientRequest, String userType) {
        this.id = patientRequest.getUserId() > 0 ? patientRequest.getUserId() : null;
        this.username = patientRequest.getUserName();
        this.email = patientRequest.getEmail();
        this.active = patientRequest.isStatusUser();
        this.userType = userType;

    }

    public User(User user, PatientRequest patientRequest) {
        user.username = patientRequest.getUserName();
        user.email = patientRequest.getEmail();
        user.active = patientRequest.isStatusUser();
        user.userType = UserTypeEnum.PATIENT.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
    }


    public List<UserVisitBranches> getUserVisitBranches() {
        return userVisitBranches;
    }

    public void setUserVisitBranches(List<UserVisitBranches> userVisitBranches) {
        this.userVisitBranches = userVisitBranches;
    }

    public List<DutyWithDoctor> getNurse() {
        return nurse;
    }

    public void setNurse(List<DutyWithDoctor> nurse) {
        this.nurse = nurse;
    }

    public List<DutyWithDoctor> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<DutyWithDoctor> doctor) {
        this.doctor = doctor;
    }

    public List<DepartmentUser> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentUser> departments) {
        this.departments = departments;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSystemDoctor() {
        return systemDoctor;
    }

    public void setSystemDoctor(boolean systemDoctor) {
        this.systemDoctor = systemDoctor;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public List<UserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<BranchUser> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchUser> branches) {
        this.branches = branches;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }

    public List<UserMedicalService> getMedicalServices() {
        return medicalServices;
    }

    public void setMedicalServices(List<UserMedicalService> medicalServices) {
        this.medicalServices = medicalServices;
    }

    public List<UserDutyShift> getDutyShifts() {
        return dutyShifts;
    }

    public void setDutyShifts(List<UserDutyShift> dutyShifts) {
        this.dutyShifts = dutyShifts;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public User getPrimaryDoctor() {
        return primaryDoctor;
    }

    public void setPrimaryDoctor(User primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    }
}
