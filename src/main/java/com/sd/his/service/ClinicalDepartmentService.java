package com.sd.his.service;

import com.sd.his.model.ClinicalDepartment;
import com.sd.his.repositories.ClinicalDepartmentRepository;
import com.sd.his.request.ClinicalDepartmentCreateRequest;
import com.sd.his.request.ClinicalDepartmentUpdateRequest;
import com.sd.his.wrapper.ClinicalDepartmentWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
 * @author    : Arif Heer
 * @Date      : 04/5/2018
 * @version   : ver. 1.0.0
 *
 * ________________________________________________________________________________________________
 *
 *  Developer    Date       Version  Operation  Description
 * ________________________________________________________________________________________________
 *
 *
 * ________________________________________________________________________________________________
 *
 * @Project   : HIS
 * @Package   : com.sd.his.service
 * @FileName  : DepartmentService
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Service
public class ClinicalDepartmentService {

    @Autowired
    private ClinicalDepartmentRepository departmentRepository;

    private final Logger logger = LoggerFactory.getLogger(ClinicalDepartmentService.class);

    public List<ClinicalDepartmentWrapper> getAllActiveClinicalDepartments() {
        List<ClinicalDepartment> dpts = departmentRepository.findAllByDeletedFalse();
        List<ClinicalDepartmentWrapper> dptsWrappers = new ArrayList<>();

        for (ClinicalDepartment cd : dpts) {
            ClinicalDepartmentWrapper dpt = new ClinicalDepartmentWrapper(cd);
            dptsWrappers.add(dpt);
        }
        return dptsWrappers;
    }

    public List<ClinicalDepartmentWrapper> getAllActiveClinicalDepartment(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        List<ClinicalDepartment> dpts = departmentRepository.findAllByDeletedFalseOrderByNameAsc(pageable);
        List<ClinicalDepartmentWrapper> dptsWrappers = new ArrayList<>();

        for (ClinicalDepartment cd : dpts) {
            //#TODO need to set branch id
            ClinicalDepartmentWrapper dpt = new ClinicalDepartmentWrapper(cd);
            dptsWrappers.add(dpt);
        }
        return dptsWrappers;
    }

    public int countAllClinicalDepartments() {
        return departmentRepository.findAllByDeletedFalse().size();
    }

    public ClinicalDepartment findClinicalDepartmentById(long dptId) {
        return departmentRepository.findByIdAndDeletedFalse(dptId);
    }

    public boolean isClinicalDepartmentByNameAndNotIdExist(String name, long dptId) {
        return departmentRepository.findByNameAndIdNotAndDeletedFalse(name, dptId) == null ? true : false;
    }

    @Transactional(rollbackOn = Throwable.class)
    public boolean deleteDepartment(ClinicalDepartment department) {
        boolean isDeleted;
        department.setDeleted(true);
        department = departmentRepository.save(department);
        if (department.isDeleted()) {
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }

    public int countSearchedClinicalDepartments(String name) {
        return departmentRepository.findByNameDeletedFalse(name).size();
    }

    public List<ClinicalDepartmentWrapper> getPageableSearchedDepartment(int offset, int limit, String name) {
        Pageable pageable = new PageRequest(offset, limit);
        return departmentRepository.findByNameDeletedFalse(pageable, name);
    }

    @Transactional(rollbackOn = Throwable.class)
    public ClinicalDepartment saveClinicalDepartment(ClinicalDepartmentCreateRequest createRequest) {
        ClinicalDepartment dpt = new ClinicalDepartment(createRequest);

        return departmentRepository.save(dpt);
    }

    @Transactional(rollbackOn = Throwable.class)
    public ClinicalDepartment updateClinicalDepartment(ClinicalDepartmentUpdateRequest updateRequest) {
        ClinicalDepartment dpt = departmentRepository.findOne(updateRequest.getId());
        dpt.setName(updateRequest.getName());
        dpt.setDescription(updateRequest.getDescription());
        dpt.setDeleted(false);
        dpt.setActive(updateRequest.isActive());
        dpt.setUpdatedOn(System.currentTimeMillis());

        return departmentRepository.save(dpt);
    }

    public ClinicalDepartment findClinicalDepartmentByName(String name) {
        return departmentRepository.findByNameAndDeletedFalse(name);
    }
}