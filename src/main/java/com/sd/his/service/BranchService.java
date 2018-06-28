package com.sd.his.service;

import com.sd.his.enums.PropertyEnum;
import com.sd.his.model.*;
import com.sd.his.repositories.BranchRepository;
import com.sd.his.repositories.BranchUserRepository;
import com.sd.his.repositories.RoomRepository;
import com.sd.his.repositories.UserRepository;
import com.sd.his.request.BranchRequestWrapper;
import com.sd.his.response.BranchResponseWrapper;
import com.sd.his.wrapper.ExamRooms;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @author    : waqas kamran
 * @Date      : 17-Apr-18
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
 * @Package   : com.sd.his.*
 * @FileName  : UserAuthAPI
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class BranchService {

    private final Logger logger = LoggerFactory.getLogger(BranchService.class);

    private BranchRepository branchRepository;
    private BranchUserRepository branchUserRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;

    public BranchService(BranchRepository branchRepository, BranchUserRepository branchUserRepository,
                         UserRepository userRepository, RoomRepository roomRepository) {
        this.branchRepository = branchRepository;
        this.branchUserRepository = branchUserRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public Branch saveBranch(BranchRequestWrapper branchRequestWrapper) {
        User user;
        Branch branch = new Branch();
        branch.setName(branchRequestWrapper.getBranchName());
        branch.setCreatedOn(System.currentTimeMillis());
        branch.setUpdatedOn(System.currentTimeMillis());
        branch.setActive(true);
        branch.setDeleted(false);
        branch.setNoOfRooms(branchRequestWrapper.getNoOfExamRooms());
        branch.setAddress(branchRequestWrapper.getAddress());
        branch.setCity(branchRequestWrapper.getCity());
        branch.setState(branchRequestWrapper.getState());
        branch.setZipCode(branchRequestWrapper.getZipCode());
        branch.setOfficePhone(branchRequestWrapper.getOfficePhone());
        branch.setOfficeStartTime(branchRequestWrapper.getOfficeHoursStart());
        branch.setOfficeEndTime(branchRequestWrapper.getOfficeHoursEnd());
        branch.setCountry(branchRequestWrapper.getCountry());
        branch.setBillingName(branchRequestWrapper.getBillingName());
        branch.setBillingTaxId(branchRequestWrapper.getBillingTaxID());
        branch.setBillingBranchName(branchRequestWrapper.getBillingBranch());
        branch.setAllowOnlineSchedule(branchRequestWrapper.isAllowOnlineSchedulingInBranch());
        branch.setShowBranchInfoOnline(branchRequestWrapper.isShowBranchOnline());
        branch.setFax(branchRequestWrapper.getFax());
        branch.setFormattedAddress(branchRequestWrapper.getFormattedAddress());
        user = userRepository.findById(branchRequestWrapper.getPrimaryDoctor());
        String userName = user.getUsername();
        if (userName.equalsIgnoreCase(PropertyEnum.PRIMARY_DOCTOR.getValue())) {
            user = new User();
            Profile profile = new Profile();
            user.setUsername(branchRequestWrapper.getBranchName() + "BRANCH");
            user.setUserType(PropertyEnum.USER_TYPE_DOCTOR.getValue());
            user.setEmail(generateEmail("gmail.com", 6));
            user.setSystemDoctor(false);
            user.setPassword(new BCryptPasswordEncoder().encode(branchRequestWrapper.getBranchName()));
            profile.setCellPhone(branchRequestWrapper.getOfficePhone());
            profile.setHomePhone(branchRequestWrapper.getOfficePhone());
            profile.setActive(true);
            profile.setDeleted(false);
            profile.setType(PropertyEnum.USER_TYPE_DOCTOR.getValue());
            profile.setUpdatedOn(System.currentTimeMillis());
            profile.setCreatedOn(System.currentTimeMillis());
            profile.setFirstName(branchRequestWrapper.getBranchName());
            profile.setLastName(branchRequestWrapper.getBranchName());
            profile.setCountry(branchRequestWrapper.getCity());
            profile.setAddress(branchRequestWrapper.getAddress());
            profile.setState(branchRequestWrapper.getState());
            profile.setStatus(PropertyEnum.STATUS.getValue());

            user.setProfile(profile);
            userRepository.save(user);
        }
        branchRepository.save(branch);
        List<ExamRooms> exRooms = new ArrayList<>(Arrays.asList(branchRequestWrapper.getExamRooms()));
        BranchUser branchUser = new BranchUser();
        branchUser.setPrimaryDr(true);
        branchUser.setPrimaryBranch(true);
        branchUser.setBillingBranch(true);
        branchUser.setBranch(branch);
        branchUser.setBranch(branch);
        branchUser.setUser(user);
        branchUserRepository.save(branchUser);
        for (ExamRooms ex : exRooms) {
            Room room = new Room();

            room.setAllowOnlineScheduling(ex.isAllowOnlineScheduling());
            room.setExamName(ex.getExamName());
            room.setActive(true);
            room.setDeleted(false);
            room.setCreatedOn(System.currentTimeMillis());
            room.setUpdatedOn(System.currentTimeMillis());
            room.setBranch(branch);
            roomRepository.save(room);
        }
        return branch;
    }

    public Branch findByBranchName(String name) {
        return branchRepository.findByName(name);
    }

    public List<BranchResponseWrapper> findAllBranches(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        return branchRepository.findAllByNameAndActiveTrueAndDeletedFalse(pageable);
    }

    public int totalBranches() {
        return ((int) branchRepository.count());
    }

    public Branch findById(long id) {
        return branchRepository.findByIdAndDeletedFalse(id);
    }

    public void deleteBranch(Branch branch) {
        branch.setDeleted(true);
        branchRepository.save(branch);
    }

    public BranchResponseWrapper findByID(long id) {
        Branch branch = branchRepository.findByIdAndDeletedFalse(id);
        BranchResponseWrapper branchResponseWrapper = new BranchResponseWrapper(branch);
        List<Room> roomListData = new ArrayList<>();
        for (Room room : branch.getRooms()) {
            Room room1 = new Room(room.getExamName(), room.isAllowOnlineScheduling());
            roomListData.add(room1);
        }
        branchResponseWrapper.setExamRooms(roomListData);
        BranchUser branchUser = branchUserRepository.findByBranch(branch);
        branchResponseWrapper.setUser(branchUser.getUser());
        //branchResponseWrapper.setUsername(branchUser.getUser().getUsername());

        return branchResponseWrapper;
    }

    public Branch updateBranch(BranchRequestWrapper branchRequestWrapper, Branch branch) {

        branch.setName(branchRequestWrapper.getBranchName());
        branch.setUpdatedOn(System.currentTimeMillis());
        branch.setActive(true);
        branch.setDeleted(false);
        branch.setNoOfRooms(branchRequestWrapper.getNoOfExamRooms());
        branch.setAddress(branchRequestWrapper.getAddress());
        branch.setCity(branchRequestWrapper.getCity());
        branch.setState(branchRequestWrapper.getState());
        branch.setZipCode(branchRequestWrapper.getZipCode());
        branch.setOfficePhone(branchRequestWrapper.getOfficePhone());
        branch.setOfficeStartTime(branchRequestWrapper.getOfficeHoursStart());
        branch.setOfficeEndTime(branchRequestWrapper.getOfficeHoursEnd());
        branch.setCountry(branchRequestWrapper.getCountry());
        branch.setBillingName(branchRequestWrapper.getBillingName());
        branch.setBillingTaxId(branchRequestWrapper.getBillingTaxID());
        branch.setBillingBranchName(branchRequestWrapper.getBillingBranch());
        branch.setAllowOnlineSchedule(branchRequestWrapper.isAllowOnlineSchedulingInBranch());
        branch.setShowBranchInfoOnline(branchRequestWrapper.isShowBranchOnline());
        branch.setFax(branchRequestWrapper.getFax());
        branch.setFormattedAddress(branchRequestWrapper.getFormattedAddress());

        List<ExamRooms> exRooms = new ArrayList<>(Arrays.asList(branchRequestWrapper.getExamRooms()));
        for (ExamRooms ex : exRooms) {
            Room room = new Room();
            room.setAllowOnlineScheduling(ex.isAllowOnlineScheduling());
            room.setExamName(ex.getExamName());
            room.setUpdatedOn(System.currentTimeMillis());
            room.setBranch(branch);
            roomRepository.save(room);
        }

        branchRepository.save(branch);
        return branch;
    }

    public List<String> findAllBranchName() {
        List<Branch> allBranches = branchRepository.findAllByActiveTrueAndDeletedFalse();
        List<String> branchNames = allBranches.stream()
                .filter(x -> x.getName() != null)
                .map(x -> x.getName())
                .collect(Collectors.toList());

        return branchNames;

    }

    public List<BranchResponseWrapper> searchByBranchNameAndDepartment(String name, String department, int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        logger.info("branch name" + department);

        List<Branch> allBranches = branchRepository.findByNameIgnoreCaseContainingAndActiveTrueAndDeletedFalseOrClinicalDepartments_clinicalDpt_nameIgnoreCaseContaining(name, department, pageable);

        List<BranchResponseWrapper> branchResponseWrapper = new ArrayList<>();
        for (Branch branch : allBranches) {

            BranchResponseWrapper brw = new BranchResponseWrapper(branch.getId(), branch.getName(), branch.getCountry(), branch.getCity(), branch.getNoOfRooms());
            branchResponseWrapper.add(brw);
        }
        return branchResponseWrapper;
    }

    public List<BranchResponseWrapper> getAllActiveBranches() {
        List<Branch> branch = branchRepository.findAllByActiveTrueAndDeletedFalse();
        //  return branchRepository.findAllByActiveTrueAndDeletedFalse();
        List<BranchResponseWrapper> list = new ArrayList<>();

        for (Branch branch1 : branch) {
            BranchResponseWrapper branchResponseWrapper = new BranchResponseWrapper(branch1);
            list.add(branchResponseWrapper);
        }
        return list;
    }

    private String generateEmail(String domain, int length) {
        return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyz") + "@" + domain;
    }
}