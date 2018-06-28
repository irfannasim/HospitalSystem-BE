package com.sd.his.model;

import javax.persistence.*;
import java.io.Serializable;

/*
 * @author    : Irfan Nasim
 * @Date      : 08-Jun-18
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
 * @FileName  : Branch
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Entity
@Table(name = "APPOINTMENT")
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "STATUS")  //AppointmentStatusTypeEnum
    private String status;

    @Column(name = "TYPE") //AppointmentTypeEnum
    private String type;

    @Column(name = "DURATION") //minutes
    private Long duration;
    @Column(name = "AGE")
    private Long age;

    @Column(name = "FOLLOW_UP_REMINDER")
    private Boolean followUpReminder;

    @Column(name = "FOLLOW_UP_REMINDER_REASON")
    private String followUpReasonReminder;

    @Column(name = "STARTED_ON")
    private Long startedOn;

    @Column(name = "ENDED_ON")
    private Long endedOn;

    @Column(name = "CREATED_ON")
    private Long createdOn;

    @Column(name = "UPDATED_ON")
    private Long updatedOn;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "EXAM_ROOM_ID")
    private Room room;

    @Column(name = "IS_RECURRING")
    private Boolean recurring;

    @Column(name = "RECURRING_DAYS")
    private String recurringDays; //should be save json of days

    @Column(name = "FIRST_APPOINTMENT_ON")
    private Long firstAppointmentOn;

    @Column(name = "FOLLOW_UP_DATE")
    private Long followUpDate;

    @Column(name = "LAST_APPOINTMENT_ON")
    private Long lastAppointmentOn;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default false", nullable = false)
    private boolean active;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patient=" + patient.getProfile().getFirstName() + " " + patient.getProfile().getLastName() +
                ", branch=" + branch.getName() +
                ", room=" + room.getExamName() +
                '}';
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

    public Long getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(Long followUpDate) {
        this.followUpDate = followUpDate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getFollowUpReminder() {
        return followUpReminder;
    }

    public void setFollowUpReminder(Boolean followUpReminder) {
        this.followUpReminder = followUpReminder;
    }

    public String getFollowUpReasonReminder() {
        return followUpReasonReminder;
    }

    public void setFollowUpReasonReminder(String followUpReasonReminder) {
        this.followUpReasonReminder = followUpReasonReminder;
    }

    public Long getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Long startedOn) {
        this.startedOn = startedOn;
    }

    public Long getEndedOn() {
        return endedOn;
    }

    public void setEndedOn(Long endedOn) {
        this.endedOn = endedOn;
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

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public String getRecurringDays() {
        return recurringDays;
    }

    public void setRecurringDays(String recurringDays) {
        this.recurringDays = recurringDays;
    }



    public Long getFirstAppointmentOn() {
        return firstAppointmentOn;
    }

    public void setFirstAppointmentOn(Long firstAppointmentOn) {
        this.firstAppointmentOn = firstAppointmentOn;
    }

    public Long getLastAppointmentOn() {
        return lastAppointmentOn;
    }

    public void setLastAppointmentOn(Long lastAppointmentOn) {
        this.lastAppointmentOn = lastAppointmentOn;
    }
}
