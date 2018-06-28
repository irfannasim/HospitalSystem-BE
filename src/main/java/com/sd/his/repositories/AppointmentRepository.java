package com.sd.his.repositories;

import com.sd.his.model.Appointment;
import com.sd.his.model.Tax;
import com.sd.his.wrapper.AppointmentWrapper;
import com.sd.his.wrapper.BranchWrapper;
import com.sd.his.wrapper.ExamRoomWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
 * @Package   : com.sd.his.repositories
 * @FileName  : TaxRepository
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT NEW  com.sd.his.wrapper.AppointmentWrapper(a.id, a.name, a.notes, a.reason, a.color, a.status, a.type," +
            " a.duration, a.followUpReminder, a.followUpReasonReminder, a.startedOn, a.endedOn, a.createdOn, a.updatedOn, " +
            "a.recurring, a.recurringDays, a.firstAppointmentOn, a.lastAppointmentOn, a.patient.id,a.patient.username, " +
            "a.patient.profile.firstName, a.patient.profile.lastName, a.branch.id, a.branch.name, a.room.id, a.room.examName) " +
            "FROM Appointment a WHERE a.deleted =FALSE ")
    List<AppointmentWrapper> findAllPaginatedAppointments(Pageable pageable);

    @Query("SELECT NEW  com.sd.his.wrapper.AppointmentWrapper(a.id, a.name, a.notes, a.reason, a.color, a.status, a.type," +
            " a.duration, a.followUpReminder, a.followUpReasonReminder, a.startedOn, a.endedOn, a.createdOn, a.updatedOn, " +
            "a.recurring, a.recurringDays, a.firstAppointmentOn, a.lastAppointmentOn, a.patient.id,a.patient.username, " +
            "a.patient.profile.firstName, a.patient.profile.lastName, a.branch.id, a.branch.name, a.room.id, a.room.examName) " +
            "FROM Appointment a WHERE a.deleted =FALSE and a.patient.username LIKE concat('%',:name,'%') ")
    List<AppointmentWrapper> findByNameDeletedFalse(Pageable pageable, @Param("name") String name);

    @Query("SELECT NEW  com.sd.his.wrapper.AppointmentWrapper(a.id, a.name, a.notes, a.reason, a.color, a.status, a.type," +
            " a.duration, a.followUpReminder, a.followUpReasonReminder, a.startedOn, a.endedOn, a.createdOn, a.updatedOn, " +
            "a.recurring, a.recurringDays, a.firstAppointmentOn, a.lastAppointmentOn, a.patient.id,a.patient.username, " +
            "a.patient.profile.firstName, a.patient.profile.lastName, a.branch.id, a.branch.name, a.room.id, a.room.examName) " +
            "FROM Appointment a WHERE a.deleted =FALSE and a.patient.username LIKE concat('%',:name,'%') ")
    List<Appointment> findByNameDeletedFalse(@Param("name") String name);

    Appointment findByIdAndDeletedFalse(long id);
}
