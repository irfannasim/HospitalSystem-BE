package com.sd.his.service;

import com.google.gson.Gson;
import com.sd.his.enums.AppointmentTypeEnum;
import com.sd.his.enums.UserTypeEnum;
import com.sd.his.model.*;
import com.sd.his.repositories.*;
import com.sd.his.request.BranchRequestWrapper;
import com.sd.his.request.SaveTaxRequest;
import com.sd.his.utill.APIUtil;
import com.sd.his.utill.HISCoreUtil;
import com.sd.his.wrapper.AppointmentWrapper;
import com.sd.his.wrapper.TaxWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
 * @Package   : com.sd.his.service
 * @FileName  : AppointmentService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;
    public List<AppointmentWrapper> findAllPaginatedAppointments(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
          List<AppointmentWrapper> list= appointmentRepository.findAllPaginatedAppointments(pageable);
         return appointmentRepository.findAllPaginatedAppointments(pageable);
    }

    public int countAllAppointments() {
        return appointmentRepository.findAll().size();
    }
    public Appointment saveAppointment(AppointmentWrapper appointmentWrapper){
        Appointment appointment =new Appointment();
        Branch branch = branchRepository.getOne(appointmentWrapper.getBranchId());

        appointment.setRecurringDays(new Gson().toJson(appointmentWrapper.getSelectedRecurringDays()));
        long startTime= HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getStart());
        appointment.setStartedOn(startTime);
        appointment.setEndedOn(startTime + appointmentWrapper.getDuration()*60*1000);
        appointment.setUpdatedOn(System.currentTimeMillis());
        appointment.setDeleted(false);
        appointment.setCreatedOn(System.currentTimeMillis());
        appointment.setActive(true);
        appointment.setColor(appointmentWrapper.getColor());
        appointment.setRecurring(appointmentWrapper.isRecurringAppointment());
        appointment.setDuration(appointmentWrapper.getDuration());
        appointment.setFirstAppointmentOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getFirstAppointment()));
        appointment.setLastAppointmentOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getLastAppointment()));
        appointment.setStartedOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getStart()));
        appointment.setEndedOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getEnd()));
        appointment.setNotes(appointmentWrapper.getNotes());
        appointment.setReason(appointmentWrapper.getReason());
        appointment.setType(new Gson().toJson(appointmentWrapper.getAppointmentType()));
        appointment.setStatus(appointmentWrapper.getStatus());
        appointment.setFollowUpReasonReminder(appointmentWrapper.getFollowUpReason());
        appointment.setFollowUpReminder(appointmentWrapper.getFollowUpReminder());
        appointment.setFollowUpDate(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getFollowUpDate()));
        appointment.setFollowUpReasonReminder(appointmentWrapper.getReason());
        appointment.setName(appointmentWrapper.getTitle());
        appointment.setBranch(branch);
        Room room = findExamRoomById(appointmentWrapper.getExamRoom());
        if(HISCoreUtil.isValidObject(room)){appointment.setRoom(room);}

        if(appointmentWrapper.getAppointmentType().contains(AppointmentTypeEnum.NEW_PATIENT.getValue())) {
            User user = new User();
            Profile profile = new Profile();
            user.setPassword(bCryptPasswordEncoder.encode(appointmentWrapper.getTitle()));
            user.setEmail(appointmentWrapper.getEmail());
            user.setUsername(appointmentWrapper.getTitle());
            user.setUserType(String.valueOf(UserTypeEnum.PATIENT));
            user.setDeleted(false);
            user.setActive(true);

            profile.setType(String.valueOf(UserTypeEnum.PATIENT));
            profile.setFirstName(appointmentWrapper.getTitle());
            profile.setLastName(appointmentWrapper.getTitle());
            profile.setDeleted(false);
            profile.setActive(true);
            profile.setHomePhone(appointmentWrapper.getCellPhone());
            profile.setCellPhone(appointmentWrapper.getCellPhone());
            profile.setUpdatedOn(System.currentTimeMillis());
            profile.setCreatedOn(System.currentTimeMillis());
            profile.setGender(appointmentWrapper.getGender());
          //  appointment.setAge(Long.valueOf(appointmentWrapper.getAge()).longValue());
            user.setProfile(profile);
            userRepository.save(user);
            appointment.setPatient(user);
            appointmentRepository.save(appointment);
        }else {
            User user = userRepository.findByUsername(appointmentWrapper.getPatient());
            appointment.setPatient(user);
            appointmentRepository.save(appointment);
        }
        return appointment;
    }

    public List<AppointmentWrapper> getPageableSearchedAppointments(int offset, int limit, String name) {
        Pageable pageable = new PageRequest(offset, limit);
        return appointmentRepository.findByNameDeletedFalse(pageable, name);
    }
    public int countSearchedAppointments(String name) {
        return appointmentRepository.findByNameDeletedFalse(name).size();
    }
    public Appointment findById(long id){

        return appointmentRepository.findByIdAndDeletedFalse(id);
    }

    public Appointment updateAppointment(AppointmentWrapper appointmentWrapper,Appointment appointment) {
        Branch branch = branchRepository.getOne(appointmentWrapper.getBranchId());

        appointment.setRecurringDays(new Gson().toJson(appointmentWrapper.getSelectedRecurringDays()));
        appointment.setUpdatedOn(System.currentTimeMillis());
        appointment.setDeleted(false);
        appointment.setActive(true);
        appointment.setRecurring(appointmentWrapper.isRecurringAppointment());
        appointment.setDuration(appointmentWrapper.getDuration());
       if(HISCoreUtil.isValidObject((HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getFirstAppointment())))){
           appointment.setFirstAppointmentOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getFirstAppointment()));
        }
        if(HISCoreUtil.isValidObject((HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getLastAppointment())))){
            appointment.setFirstAppointmentOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getLastAppointment()));
        }
       // appointment.setLastAppointmentOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getFollowUpDate()));
    //    appointment.setStartedOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getStart()));
      //  appointment.setEndedOn(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getEnd()));
        appointment.setNotes(appointmentWrapper.getNotes());
        appointment.setColor(appointmentWrapper.getColor());
        appointment.setReason(appointmentWrapper.getReason());
        appointment.setType(new Gson().toJson(appointmentWrapper.getAppointmentType()));
        appointment.setStatus(appointmentWrapper.getStatus());
        appointment.setFollowUpReasonReminder(appointmentWrapper.getFollowUpReason());
        appointment.setFollowUpReminder(appointmentWrapper.getFollowUpReminder());
     //   appointment.setFollowUpDate(HISCoreUtil.convertDateToMilliSeconds(appointmentWrapper.getFollowUpDate()));
        appointment.setFollowUpReasonReminder(appointmentWrapper.getReason());
        appointment.setName(appointmentWrapper.getTitle());
        appointment.setBranch(branch);
        Room room = findExamRoomById(appointmentWrapper.getExamRoom());
        if(HISCoreUtil.isValidObject(room)){appointment.setRoom(room);}

        if(appointmentWrapper.getAppointmentType().contains(AppointmentTypeEnum.NEW_PATIENT.getValue())) {
            User user = userRepository.findByUsernameOrEmail(appointmentWrapper.getPatient(), appointmentWrapper.getEmail());
            Profile profile = user.getProfile();
            user.setUsername(appointmentWrapper.getPatient());
            user.setUserType(String.valueOf(UserTypeEnum.PATIENT));
            user.setDeleted(false);
            user.setActive(true);
            appointment.setAge(Long.valueOf(appointmentWrapper.getAge()).longValue());

            profile.setType(String.valueOf(UserTypeEnum.PATIENT));
            profile.setFirstName(appointmentWrapper.getTitle());
            profile.setLastName(appointmentWrapper.getTitle());
            profile.setDeleted(false);
            profile.setActive(true);
            profile.setHomePhone(appointmentWrapper.getCellPhone());
            profile.setCellPhone(appointmentWrapper.getCellPhone());
            profile.setUpdatedOn(System.currentTimeMillis());
            profile.setGender(appointmentWrapper.getGender());
            user.setProfile(profile);
            userRepository.save(user);
            appointment.setPatient(user);
            appointmentRepository.save(appointment);
        }else {
            //User user = userRepository.findByUsername(appointmentWrapper.getPatient());
            //appointment.setPatient(user);
            appointmentRepository.save(appointment);}
        return appointment;
    }

    public Room findExamRoomById(Long id) {
        if (id != null) {
            Room room = roomRepository.getOne(id);
            return room;
        }
        return null;
    }

    public void deleteAppointment(Appointment appointment) {
        appointment.setDeleted(true);
        appointmentRepository.save(appointment);
    }

}
