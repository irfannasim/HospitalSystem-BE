package com.sd.his.repositories;

import com.sd.his.model.ClinicalDepartment;
import com.sd.his.wrapper.ClinicalDepartmentWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @author    : Arif Heer
 * @Date      : 4/10/2018
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
 * @FileName  : ClinicalDepartmentRepository
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Repository
public interface ClinicalDepartmentRepository extends JpaRepository<ClinicalDepartment, Long> {

    List<ClinicalDepartment> findAllByActiveTrueAndDeletedFalseOrderByNameAsc(Pageable pageable);
    List<ClinicalDepartment> findAllByDeletedFalseOrderByNameAsc(Pageable pageable);
    List<ClinicalDepartment> findAllByDeletedFalse();

    ClinicalDepartment findByIdAndDeletedFalse(long id);
    ClinicalDepartment findByNameAndIdNotAndDeletedFalse(String name,long id);

    @Query("SELECT new com.sd.his.wrapper.ClinicalDepartmentWrapper(cd.id,cd.name,cd.description,cd.active,cd.deleted)" +
            " FROM ClinicalDepartment cd where cd.deleted = FALSE and cd.name LIKE  CONCAT('%',:name,'%') ")
    List<ClinicalDepartmentWrapper> findByNameDeletedFalse(Pageable pageable, @Param("name") String name);

    @Query("SELECT new com.sd.his.wrapper.ClinicalDepartmentWrapper(cd.id,cd.name,cd.description,cd.active,cd.deleted)" +
            " FROM ClinicalDepartment cd where cd.deleted = FALSE and cd.name LIKE  CONCAT('%',:name,'%') ")
    List<ClinicalDepartment> findByNameDeletedFalse(@Param("name") String name);

    ClinicalDepartment findByNameAndDeletedFalse(String name);

    ClinicalDepartment findById(long id);

    List<ClinicalDepartment> findAllByIdIn(List<Long> ids);

}
