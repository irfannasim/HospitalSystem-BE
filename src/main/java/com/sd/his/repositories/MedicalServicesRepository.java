package com.sd.his.repositories;

import com.sd.his.model.MedicalService;
import com.sd.his.wrapper.MedicalServiceWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
 * @FileName  : MedicalServicesRepository
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Repository
public interface MedicalServicesRepository extends JpaRepository<MedicalService, Long> {

    @Query("SELECT new com.sd.his.wrapper.MedicalServiceWrapper(ms.id, ms.title, ms.fee, ms.cost, ms.status, b.id, b.name, cd.id, cd.name, t.id, t.rate, ms.description, ms.duration) " +
            "FROM MedicalService ms JOIN ms.departments cdms JOIN cdms.clinicalDpt cd JOIN ms.branches bms JOIN bms.branch b JOIN ms.tax t ")
    List<MedicalServiceWrapper> findAllPaginated(Pageable pageable);



    @Query("SELECT new com.sd.his.wrapper.MedicalServiceWrapper(ms.id, ms.title, ms.fee, ms.cost, ms.status, b.id, b.name, cd.id, cd.name, t.id, t.rate, ms.description, ms.duration) " +
            "FROM MedicalService ms JOIN ms.departments cdms JOIN cdms.clinicalDpt cd JOIN ms.branches bms JOIN bms.branch b JOIN ms.tax t " +
            "WHERE ms.title = :title AND b.id = :branchId AND cd.id = :dptId AND ms.status = TRUE")
    MedicalServiceWrapper findOneByTitleAndDptAndBranch(@Param("title") String title, @Param("branchId") long branchId, @Param("dptId") long dptId);

    @Query("SELECT new com.sd.his.wrapper.MedicalServiceWrapper(ms.id, ms.title, ms.fee, ms.cost, ms.status, b.id, b.name, cd.id, cd.name, t.id, t.rate, ms.description, ms.duration) " +
            "FROM MedicalService ms JOIN ms.departments cdms JOIN cdms.clinicalDpt cd JOIN ms.branches bms JOIN bms.branch b JOIN ms.tax t ")
    List<MedicalServiceWrapper> findAllMedicalServiceWrappers();

    MedicalService findByTitleAndDeletedFalse(String title);

    @Query("SELECT new com.sd.his.wrapper.MedicalServiceWrapper(ms.id, ms.title, ms.fee, ms.cost, ms.status, b.id, b.name, cd.id, cd.name, t.id, t.rate, ms.description, ms.duration) " +
            "FROM MedicalService ms JOIN ms.departments cdms JOIN cdms.clinicalDpt cd JOIN ms.branches bms JOIN bms.branch b JOIN ms.tax t " +
            "WHERE ms.id = :msId AND ms.deleted = FALSE")
    MedicalServiceWrapper findOneByIdAndDeletedFalse(@Param("msId") Long msId);

    MedicalService findByIdNotAndTitleAndDeletedFalse(long id, String title);


    @Query("SELECT new com.sd.his.wrapper.MedicalServiceWrapper(ms.id, ms.title, ms.fee, ms.cost, ms.status, b.id, b.name, cd.id, cd.name, t.id, t.rate, ms.description, ms.duration) " +
            "FROM MedicalService ms JOIN ms.departments cdms JOIN cdms.clinicalDpt cd JOIN ms.branches bms JOIN bms.branch b JOIN ms.tax t " +
            "WHERE ms.deleted = false AND (ms.title LIKE  CONCAT('%',:serviceName,'%') OR b.id = :branchId OR cd.id = :departmentId OR ms.id = :serviceId OR ms.fee = :serviceFee)")
    List<MedicalServiceWrapper> findAllByParam(@Param("serviceId") Long serviceId,
                                         @Param("serviceName") String serviceName,
                                         @Param("branchId") Long branchId,
                                         @Param("departmentId") Long departmentId,
                                         @Param("serviceFee") Double serviceFee,
                                         Pageable pageable);

    @Query("SELECT new com.sd.his.wrapper.MedicalServiceWrapper(ms.id, ms.title, ms.fee, ms.cost, ms.status, b.id, b.name, cd.id, cd.name, t.id, t.rate, ms.description, ms.duration) " +
            "FROM MedicalService ms JOIN ms.departments cdms JOIN cdms.clinicalDpt cd JOIN ms.branches bms JOIN bms.branch b JOIN ms.tax t " +
            "WHERE  ms.deleted = false AND (ms.title LIKE  CONCAT('%',:serviceName,'%') OR b.id = :branchId OR cd.id = :departmentId OR ms.id = :serviceId OR ms.fee = :serviceFee)")
    List<MedicalServiceWrapper> countAllByParam(@Param("serviceId") Long serviceId,
                                                @Param("serviceName") String serviceName,
                                                @Param("branchId") Long branchId,
                                                @Param("departmentId") Long departmentId,
                                                @Param("serviceFee") Double serviceFee);

    List<MedicalService> findAllByIdIn(List<Long> ids);

    List<MedicalService> findAllByDeletedFalse();


}
