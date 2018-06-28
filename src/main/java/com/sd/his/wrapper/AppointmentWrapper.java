package com.sd.his.wrapper;
import java.util.List;

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
 * @Package   : com.sd.his.wrapper
 * @FileName  : AppointmentWrapper
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class AppointmentWrapper {

    private Long id;
    private String title;
    private String start;
    private String end;
    private Long examRoom;
    private Boolean draggable;
    private String patient;
    private String notes;
    private String reason;
    private String color;
    private String status;//AppointmentStatusTypeEnum
    private Long duration; //minutes
    private Boolean followUpReminder;
    private String followUpReason;
    private Long startedOn;
    private Long endedOn;
    private Long createdOn;
    private Long updatedOn;
    private boolean recurringAppointment;
    private String recurseEvery;
    private Long recurringPeriod;
    private String gender;
    private String age;
    private String cellPhone;
    private String firstAppointment;
    private String lastAppointment;
    private String followUpDate;
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private Long branchId;
    private Long roomId;
    private String email;
    private List<String> selectedRecurringDays;
    private List<String> appointmentType;
    private String examName;
    private String branchName;
    private String type;
    private Long firstAppointmentOn;
    private Long lastAppointmentOn;

    public AppointmentWrapper() {
    }

    public AppointmentWrapper(Long id, String title, String notes, String reason,String color,String status,String appointmentType,Long duration,
                              Boolean followUpReminder,String followUpReasonReminder,Long startedOn,Long endedOn,Long createdOn, Long updatedOn,
                              boolean recurring,String recurringDays,Long firstAppointmentOn,Long lastAppointmentOn,Long patientId,String patient, String patientFirstName,
                              String patientLastName,Long branchId,String branchName,Long roomId,String examName){
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.draggable = draggable;
        this.patient = patient;
        this.notes = notes;
        this.reason = reason;
        this.color = color;
        this.status = status;
        this.type = appointmentType;
        this.duration = duration;
        this.followUpReminder = followUpReminder;
    //    this.followUpReason = followUpReasonReminder;
        this.startedOn = startedOn;
        this.endedOn = endedOn;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
      //  this.recurring = recurring;
     //   this.recurringDays = recurringDays;
        this.recurringPeriod = recurringPeriod;
        this.firstAppointmentOn = firstAppointmentOn;
        this.lastAppointmentOn = lastAppointmentOn;
        this.patientId = patientId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.branchId = branchId;

        this.roomId = roomId;
        this.roomId = roomId;
        this.examName=examName;
        this.branchName =branchName;
    }

    @Override
    public String toString() {
        return "AppointmentWrapper{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", notes='" + notes + '\'' +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", duration=" + duration +
                '}';
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

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getRecurseEvery() {
        return recurseEvery;
    }

    public void setRecurseEvery(String recurseEvery) {
        this.recurseEvery = recurseEvery;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getExamRoom() {
        return examRoom;
    }

    public void setExamRoom(Long examRoom) {
        this.examRoom = examRoom;
    }

    public Boolean getDraggable() {
        return draggable;
    }

    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
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

    public String getFollowUpReason() {
        return followUpReason;
    }

    public void setFollowUpReason(String followUpReason) {
        this.followUpReason = followUpReason;
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

    public boolean isRecurringAppointment() {
        return recurringAppointment;
    }

    public void setRecurringAppointment(boolean recurringAppointment) {
        this.recurringAppointment = recurringAppointment;
    }
    public Long getRecurringPeriod() {
        return recurringPeriod;
    }

    public void setRecurringPeriod(Long recurringPeriod) {
        this.recurringPeriod = recurringPeriod;
    }

    public String getFirstAppointment() {
        return firstAppointment;
    }

    public void setFirstAppointment(String firstAppointment) {
        this.firstAppointment = firstAppointment;
    }

    public String getLastAppointment() {
        return lastAppointment;
    }

    public void setLastAppointment(String lastAppointment) {
        this.lastAppointment = lastAppointment;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public List<String> getSelectedRecurringDays() {
        return selectedRecurringDays;
    }

    public void setSelectedRecurringDays(List<String> selectedRecurringDays) {
        this.selectedRecurringDays = selectedRecurringDays;
    }

    public List<String> getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(List<String> appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
