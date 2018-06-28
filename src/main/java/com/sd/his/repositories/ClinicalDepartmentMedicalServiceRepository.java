package com.sd.his.repositories;

import com.sd.his.model.ClinicalDepartmentMedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @author    : Irfan Nasim
 * @Date      : 15-May-2018
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
 * @Package   : com.sd.his.repositories
 * @FileName  : ClinicalDepartmentMedicalServiceRepository
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Repository
public interface ClinicalDepartmentMedicalServiceRepository extends JpaRepository<ClinicalDepartmentMedicalService, Long> {

    List<ClinicalDepartmentMedicalService> findAllByClinicalDpt_IdAndMedicalService_Id(long dptId, long msId);

    ClinicalDepartmentMedicalService findByMedicalService_Id(long id);
}
