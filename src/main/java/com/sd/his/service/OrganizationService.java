package com.sd.his.service;/*
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

import com.sd.his.enums.PropertyEnum;
import com.sd.his.model.*;
import com.sd.his.repositories.*;
import com.sd.his.request.OrganizationRequestWrapper;
import com.sd.his.response.OrganizationResponseWrapper;
import com.sd.his.wrapper.TimezoneWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private OrganizationSpecialtyRepository organizationSpecialtyRepository;
    @Autowired
    private TimezoneRepository timezoneRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private BranchUserRepository branchUserRepository;

    public Organization saveOrganization(OrganizationRequestWrapper organizationRequestWrapper) {
        Branch branch;
        String pBranch = organizationRequestWrapper.getDefaultBranch();
        User user = new User();
        user.setUserType("admin");
        user.setEmail(organizationRequestWrapper.getEmail());
        user.setUsername(organizationRequestWrapper.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(organizationRequestWrapper.getPassword()));
        user.setActive(true);
        user.setDeleted(false);
        Profile profile = new Profile();
        profile.setFirstName(organizationRequestWrapper.getFirstName());
        profile.setLastName(organizationRequestWrapper.getLastName());
        profile.setType("admin");
        profile.setUpdatedOn(System.currentTimeMillis());
        profile.setCreatedOn(System.currentTimeMillis());
        profile.setActive(true);
        profile.setDeleted(false);
        profile.setHomePhone(organizationRequestWrapper.getHomePhone());
        profile.setCellPhone(organizationRequestWrapper.getCellPhone());
        user.setProfile(profile);
        userRepository.save(user);

        int primarBranchId = Integer.parseInt(pBranch);
        branch = branchRepository.findByIdAndDeletedFalse(primarBranchId);
        String brName = branch.getName();
        if (brName.equalsIgnoreCase(PropertyEnum.PRIMARY_BRANCH.getValue())) {
            branch = new Branch();
            branch.setDeleted(false);
            branch.setActive(true);
            branch.setCreatedOn(System.currentTimeMillis());
            branch.setUpdatedOn(System.currentTimeMillis());
            branch.setOfficePhone(organizationRequestWrapper.getHomePhone());
            branch.setName("PrimaryBranch" + organizationRequestWrapper.getFirstName());
            branch.setNoOfRooms(1);
            branch.setBillingBranchName("PB" + organizationRequestWrapper.getFirstName());
            branch.setBillingName("PB" + organizationRequestWrapper.getFirstName());
            branch.setFax("PB" + organizationRequestWrapper.getFirstName());
            branch.setBillingTaxId(organizationRequestWrapper.getFirstName());
            branch.setSystemBranch(false);
            branchRepository.save(branch);
            BranchUser branchUser = new BranchUser();
            branchUser.setPrimaryDr(true);
            branchUser.setBillingBranch(true);
            branchUser.setPrimaryBranch(true);
            branchUser.setUser(user);
            branchUser.setBranch(branch);
            branchUserRepository.save(branchUser);
        }

        Organization organization = new Organization();
        organization.setActive(true);
        organization.setCompanyName(organizationRequestWrapper.getCompanyName());
        organization.setWebsite(organizationRequestWrapper.getWebsite());
        organization.setHomePhone(organizationRequestWrapper.getHomePhone());
        organization.setCellPhone(organizationRequestWrapper.getCellPhone());
        organization.setOfficePhone(organizationRequestWrapper.getOfficePhone());
        organization.setAptSerialStart(organizationRequestWrapper.getAppointmentSerial());
        organization.setDefaultBranch(organizationRequestWrapper.getDefaultBranch());
        organization.setDurationOFExam(organizationRequestWrapper.getDurationOfExam());
        organization.setDurationFollowUp(organizationRequestWrapper.getFollowUpExam());
        organization.setTimezone(organizationRequestWrapper.getTimeZone());
        organization.setUser(user);
        organizationRepository.save(organization);

        Speciality speciality = new Speciality();
        speciality.setActive(true);
        speciality.setCreatedOn(System.currentTimeMillis());
        speciality.setDeleted(false);
        speciality.setDescription(organizationRequestWrapper.getSpecialty());
        speciality.setName(organizationRequestWrapper.getSpecialty());
        specialtyRepository.save(speciality);

        OrganizationSpecialty organization_specialty = new OrganizationSpecialty();
        organization_specialty.setOrganization(organization);
        organization_specialty.setSpeciality(speciality);
        organizationSpecialtyRepository.save(organization_specialty);

        return organization;
    }

    public Organization saveOrganizationWithExistingUser(User user, OrganizationRequestWrapper organizationRequestWrapper) {

        Organization organization = new Organization();
        organization.setActive(true);
        organization.setCompanyName(organizationRequestWrapper.getCompanyName());
        organization.setWebsite(organizationRequestWrapper.getWebsite());
        organization.setHomePhone(organizationRequestWrapper.getHomePhone());
        organization.setCellPhone(organizationRequestWrapper.getCellPhone());
        organization.setOfficePhone(organizationRequestWrapper.getOfficePhone());
        organization.setAptSerialStart(organizationRequestWrapper.getAppointmentSerial());
        organization.setDefaultBranch(organizationRequestWrapper.getDefaultBranch());
        organization.setDurationOFExam(organizationRequestWrapper.getDurationOfExam());
        organization.setDurationFollowUp(organizationRequestWrapper.getFollowUpExam());
        organization.setTimezone(organizationRequestWrapper.getTimeZone());
        organization.setUser(user);
        organizationRepository.save(organization);

        Speciality speciality = new Speciality();
        speciality.setActive(true);
        speciality.setCreatedOn(System.currentTimeMillis());
        speciality.setDeleted(false);
        speciality.setDescription(organizationRequestWrapper.getSpecialty());
        speciality.setName(organizationRequestWrapper.getSpecialty());
        specialtyRepository.save(speciality);

        OrganizationSpecialty organization_specialty = new OrganizationSpecialty();
        organization_specialty.setOrganization(organization);
        organization_specialty.setSpeciality(speciality);
        organizationSpecialtyRepository.save(organization_specialty);
        return organization;
    }


    public List<TimezoneWrapper> getAllTimeZone() {
        return timezoneRepository.findAllByCountryCode();
    }

    public List<OrganizationResponseWrapper> getAllActiveOrganizations() {
        List<OrganizationResponseWrapper> organizationResponseWrappers = new ArrayList<>();
        for (Organization organization : organizationRepository.findAllByActiveTrueAndDeletedFalse()) {
            OrganizationResponseWrapper organizationResponseWrapper = new OrganizationResponseWrapper(organization);
            organizationResponseWrappers.add(organizationResponseWrapper);
        }
        return organizationResponseWrappers;
    }

    public List<OrganizationResponseWrapper> findAllPaginatedOrganization(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        return organizationRepository.findAllByNameAndActiveTrueAndDeletedFalse(pageable);
    }

    public int totalOrganizations() {
        return organizationRepository.countAllByActiveTrueAndDeletedFalse();
    }

    public OrganizationResponseWrapper getOrganizationByIdWithResponse(long id) {
        Organization organization = organizationRepository.findOne(id);
        OrganizationSpecialty organizationSpecialty = organizationSpecialtyRepository.findByOrganization(organization);
        Speciality speciality = organizationSpecialty.getSpeciality();
        OrganizationResponseWrapper organizationResponseWrapper = new OrganizationResponseWrapper(organization);
        organizationResponseWrapper.setSpeciality(speciality);
        return organizationResponseWrapper;
    }

    public Organization getByID(long id) {
        return organizationRepository.getOne(id);
    }

    public OrganizationRequestWrapper updateOrganization(OrganizationRequestWrapper organizationRequestWrapper, Organization organization) {
        organization.setCompanyName(organizationRequestWrapper.getCompanyName());
        organization.setWebsite(organizationRequestWrapper.getWebsite());
        organization.setHomePhone(organizationRequestWrapper.getHomePhone());
        organization.setCellPhone(organizationRequestWrapper.getCellPhone());
        organization.setOfficePhone(organizationRequestWrapper.getOfficePhone());
        organization.setAptSerialStart(organizationRequestWrapper.getAppointmentSerial());
        organization.setDefaultBranch(organizationRequestWrapper.getDefaultBranch());
        organization.setDurationOFExam(organizationRequestWrapper.getDurationOfExam());
        organization.setDurationFollowUp(organizationRequestWrapper.getFollowUpExam());
        organization.setTimezone(organizationRequestWrapper.getTimeZone());

        OrganizationSpecialty organizationSpecialty = organizationSpecialtyRepository.findByOrganization(organization);
        Speciality speciality = organizationSpecialty.getSpeciality();
        speciality.setName(organizationRequestWrapper.getSpecialty());
        speciality.setDescription(organizationRequestWrapper.getSpecialty());
        specialtyRepository.save(speciality);
        organizationRepository.save(organization);
        return organizationRequestWrapper;
    }

    public Organization findOrgnazatinoByCompanyName(String companyName) {
        return organizationRepository.findByCompanyName(companyName);
    }
}